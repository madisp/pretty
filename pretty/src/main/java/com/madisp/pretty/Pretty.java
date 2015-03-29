package com.madisp.pretty;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.android.internal.policy.impl.PhoneWindow;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

/**
 * TODO: write a general overview of pretty.
 */
public class Pretty {
    private final Collection<Decor> decors = new ArrayList<Decor>();

    private Pretty(@NotNull final Activity activity) {
        // ooh, ugly
        try {
            Field f = PhoneWindow.class.getDeclaredField("mLayoutInflater");
            f.setAccessible(true);
            f.set(activity.getWindow(), new PrettyLayoutInflater(this, activity));
        } catch (Exception e) {
            throw new IllegalStateException("Failed invoking Pretty.wrap on an Activity.", e);
        }
    }

    /**
     * "Infect" a LayoutInflater in an Activity with a new Pretty instance.
     * @param activity activity whose LayoutInflater to mangle
     * @return An instance of Pretty, see {@link com.madisp.pretty.Pretty#with(Decor)}
     */
    @NotNull
    public static Pretty wrap(@NotNull Activity activity) {
        // hide the constructor behind a more stable public "API"
        return new Pretty(activity);
    }

    /**
     * Add a decorator to the filter chain.
     * @param decor The decorator to add
     * @return Pretty instance used, allows one to chain multiple with calls.
     */
    @NotNull
    public Pretty with(@NotNull Decor decor) {
        decors.add(decor);
        return this;
    }

    /**
     * @return The list of decorators registered so far.
     */
    @NotNull
    public Collection<Decor> getDecors() {
        return decors;
    }
}

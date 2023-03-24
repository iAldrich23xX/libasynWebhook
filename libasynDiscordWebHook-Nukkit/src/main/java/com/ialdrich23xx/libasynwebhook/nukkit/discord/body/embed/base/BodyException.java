package com.ialdrich23xx.libasynwebhook.nukkit.discord.body.embed.base;

public class BodyException extends RuntimeException
{
    private final Throwable cause;

    public BodyException(Throwable throwable)
    {
        this.cause = throwable;
    }

    public BodyException()
    {
        this.cause = null;
    }

    public BodyException(Throwable cause, String message)
    {
        super(message);
        this.cause = cause;
    }

    public BodyException(String message)
    {
        super(message);
        this.cause = null;
    }
}

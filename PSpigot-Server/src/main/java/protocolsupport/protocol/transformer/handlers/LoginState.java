/*
 * Decompiled with CFR 0_132.
 */
package protocolsupport.protocol.transformer.handlers;

public enum LoginState {
    HELLO,
    ONLINEMODERESOLVE,
    KEY,
    AUTHENTICATING,
    READY_TO_ACCEPT,
    ACCEPTED;
    

    private LoginState() {
    }
}


package com.calyr.network;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;

@com.squareup.moshi.JsonClass(generateAdapter = true)
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\t"}, d2 = {"Lcom/calyr/network/AvatarResponseDto;", "", "login", "", "url", "(Ljava/lang/String;Ljava/lang/String;)V", "getLogin", "()Ljava/lang/String;", "getUrl", "network_debug"})
public final class AvatarResponseDto {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String login = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String url = null;
    
    public AvatarResponseDto(@com.squareup.moshi.Json(name = "login")
    @org.jetbrains.annotations.NotNull
    java.lang.String login, @com.squareup.moshi.Json(name = "avatar_url")
    @org.jetbrains.annotations.NotNull
    java.lang.String url) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLogin() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUrl() {
        return null;
    }
}
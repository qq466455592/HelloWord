package com.mobile.meishang.core.network;

import android.graphics.Bitmap;

public interface ImageDataProtocol<D>
{
    public D handle(Bitmap bitmap) throws ZLNetworkException;

}

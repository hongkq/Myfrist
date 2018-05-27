package com.example.a666.myfrist.Heart;

import android.util.Log;

/**
 * Created by 洪凯庆666 on 2018/5/5.
 */

public abstract class ImageProcessing {
    private static int decodeYUV420SPtoRedSum(byte[] yunv420sp, int width, int height) {
        if (yunv420sp == null) return 0;
        final int frameSize = width * height;
        int sum = 0;
        for (int j = 0, yp = 0; j < height; j++) {
            int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
            for (int i = 0; i < width; i++, yp++) {
                int y = (0xff & ((int) yunv420sp[yp])) - 16;

                if (y < 0) y = 0;
                if ((i & 1) == 0) {
                    v = (0xff & yunv420sp[uvp++]) - 128;
                    u = (0xff & yunv420sp[uvp++]) - 128;

                }
                int r = (int) (y + 1.14 * v);
                int g = (int) (y - 0.394 * u - 0.581 * v);
                int b = (int) (u + 2.203 * v);
                if (r > 170) {
                    sum += (int) ((r * 0.3) + (g * 0.59) + (b * 0.11));
                }
            }
        }
        return sum;
    }

    public static int decodeYUV20SPtoRedAvg(byte[] yuv420sp, int width, int height) {
        if (yuv420sp == null) return 0;
        final int frameSize = width * height;
        int sum = decodeYUV420SPtoRedSum(yuv420sp, width, height);
        Log.i("decode", "decode==" + sum);
        Log.i("decode", "frameSize==" + frameSize);
        sum = sum / frameSize;
        return sum;
    }
}


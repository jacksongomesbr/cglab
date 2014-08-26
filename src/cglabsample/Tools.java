/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cglabsample;

import Catalano.Imaging.FastBitmap;

/**
 *
 * @author Jackson
 */
public class Tools {
    public static FastBitmap cloneFastBitmap(FastBitmap source) {
        FastBitmap target = null;
        int w = source.getWidth();
        int h = source.getHeight();
        if (source.isGrayscale()) {
            int[][] pixels = new int[h][w];
            source.toArrayGray(pixels);
            target = new FastBitmap(pixels);
        } else {
            int[][][] pixels = new int[h][w][3];
            source.toArrayRGB(pixels);
            target = new FastBitmap(pixels);
        }
        return target;
    }
}

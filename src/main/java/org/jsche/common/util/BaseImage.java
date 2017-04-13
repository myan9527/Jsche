package org.jsche.common.util;

import com.google.common.math.DoubleMath;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.math.RoundingMode;

/**
 * Created by Intellij IDEA. Author myan
 * Date 2017/4/13.
 */
class BaseImage {
    private String hash;

    boolean[][] getBooleanValueArray(String hash){
        if(StringUtils.isEmpty(hash) || hash.length() > 32){
            return null;
        }
        this.hash = hash;

        boolean[][] array = new boolean[32][7];

        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 7; j++) {
                array[i][j] = false;
            }
        }

        for (int i = 0; i < hash.length(); i++) {
            boolean v =
                    DoubleMath.roundToInt(Integer.parseInt(hash.charAt(i) + "", 16) / 10.0,
                            RoundingMode.HALF_UP) > 0;
            if (i % 3 == 0) {
                array[i][0] = v;
                array[i][4] = v;
            } else if (i % 7 == 1) {
                array[i][1] = v;
                array[i][3] = v;
                array[i][5] = v;
            } else {
                array[i][2] = v;
                array[i][6] = v;
            }
        }
        return array;
    }

    Color getBackgroundColor() {
        int r = Integer.parseInt(String.valueOf(this.hash.charAt(0)), 16) * 16;
        int g = Integer.parseInt(String.valueOf(this.hash.charAt(1)), 16) * 16;
        int b = Integer.parseInt(String.valueOf(this.hash.charAt(2)), 16) * 16;

        return new Color(r, g, b);
    }

    Color getForegroundColor() {
        return new Color(255, 255, 255);
    }
}

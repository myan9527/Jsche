package org.jsche.common.util;

import com.google.common.math.DoubleMath;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.RoundingMode;

/**
 * Created by Intellij IDEA. Author myan
 * Date 2017/4/13.
 */
class AvatarGenerator {

    private BaseImage base;

    AvatarGenerator() {
        this.base = new BaseImage();
    }

    BufferedImage create(String hash, int size) {
        if(StringUtils.isEmpty(hash) || size <= 0){
            return null;
        }

        boolean[][] array = base.getBooleanValueArray(hash);
        int ratio = DoubleMath.roundToInt(size / 5.0, RoundingMode.HALF_UP);

        BufferedImage image = new BufferedImage(ratio * 5, ratio * 5, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = image.getGraphics();

        graphics.setColor(base.getBackgroundColor()); // 背景色
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        graphics.setColor(base.getForegroundColor()); // 图案前景色
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 7; j++) {
                if (array[i][j]) {
                    graphics.fillRect(j * ratio, i * ratio, ratio, ratio);
                }
            }
        }
        return image;
    }
}

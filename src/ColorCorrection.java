import org.opencv.core.Mat;

public class ColorCorrection {
    public static void main(String[] args) {
        CVUtils.loadOpenCV();

        Mat fig1 = CVUtils.readImage("imgs/Q1I1.png");

        Mat fig2 = fig1.clone();

        double contrast = 5.2;
        double brightness = 35;
        int cols = fig2.cols();
        double fadingRatio = contrast / cols;
        double[] channels;
        for (int x = 0; x < cols; x++) {
            if (brightness > 0) {
                brightness -= 0.1;
            }
            if (contrast > 0) {
                contrast -= fadingRatio;
            }
            for (int y = 0; y < fig2.rows(); y++) {
                channels = fig2.get(y, x);
                for (int k = 0; k < channels.length; k++) {
                    channels[k] = CVUtils.saturate(channels[k] * contrast + brightness);
                }
                fig2.put(y, x, channels);
            }
        }

        Mat fig3 = CVUtils.readImage("imgs/Q1I2.jpg");

        Mat flippedFig3 = CVUtils.flip(fig3, 1);

        Mat translatedFig3 = CVUtils.translate(flippedFig3, 200, 1);

        Mat scaledFig3 = CVUtils.scale(translatedFig3, fig2.size());

        Mat fig4 = CVUtils.blend(fig2, scaledFig3, 0.9, 0.2);

        // Show each image in a window for comparisons
        CVUtils.showWindow("Figure 1", fig1);
        CVUtils.showWindow("Figure 2", fig2);
        CVUtils.showWindow("Figure 3", fig3);
        CVUtils.showWindow("Figure 3 Flipped", flippedFig3);
        CVUtils.showWindow("Figure 3 Translated", translatedFig3);
        CVUtils.showWindow("Figure 3 Scaled", scaledFig3);
        CVUtils.showWindow("Figure 4", fig4);

        CVUtils.waitForKeyboardInput();
    }
}

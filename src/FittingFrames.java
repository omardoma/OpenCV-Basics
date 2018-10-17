import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

public class FittingFrames {
    public static void main(String[] args) {
        CVUtils.loadOpenCV();

        Mat fig5 = CVUtils.readImage("imgs/Q2I1.jpg");

        Mat fig6 = CVUtils.readImage("imgs/Q2I2.jpg");

        Mat fig7 = CVUtils.readImage("imgs/Q2I3.jpg");

        MatOfPoint2f srcAffine = new MatOfPoint2f(new Point[]{new Point(0, 0), new Point(fig5.cols() - 1, 0), new Point(fig5.cols() - 1, fig5.rows() - 1)});

        MatOfPoint2f targetAffine;
        Mat warpMat;

        Mat fig8 = fig6.clone();

        targetAffine = new MatOfPoint2f(new Point[]{new Point(1215, 377), new Point(1309, 377), new Point(1309, 517)});

        warpMat = Imgproc.getAffineTransform(srcAffine, targetAffine);

        Imgproc.warpAffine(fig5, fig8, warpMat, fig8.size(), Imgproc.INTER_LINEAR, Core.BORDER_TRANSPARENT);

        Mat fig9 = fig7.clone();

        targetAffine = new MatOfPoint2f(new Point[]{new Point(368, 92), new Point(705, 128), new Point(665, 560)});

        warpMat = Imgproc.getAffineTransform(srcAffine, targetAffine);

        Imgproc.warpAffine(fig5, fig9, warpMat, fig9.size(), Imgproc.INTER_LINEAR, Core.BORDER_TRANSPARENT);

        // Show each image in a window for comparisons
        CVUtils.showWindow("Figure 6", fig6);
        CVUtils.showWindow("Figure 7", fig7);
        CVUtils.showWindow("Figure 8", fig8);
        CVUtils.showWindow("Figure 9", fig9);

        CVUtils.waitForKeyboardInput();
    }
}

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

public class DifferentPerspectiveFrame {
    public static void main(String[] args) {
        CVUtils.loadOpenCV();

        Mat fig5 = CVUtils.readImage("imgs/Q2I1.jpg");

        Mat fig10 = CVUtils.readImage("imgs/Q3I1.jpg");

        Mat fig12 = fig10.clone();

        MatOfPoint2f srcAffine = new MatOfPoint2f(new Point[]{new Point(0, 0), new Point(fig5.cols() - 1, 0), new Point(fig5.cols() - 1, fig5.rows() - 1), new Point(0, fig5.rows() - 1)});

        MatOfPoint2f targetAffine = new MatOfPoint2f(new Point[]{new Point(162, 35), new Point(469, 69), new Point(464, 353), new Point(157, 389)});

        Mat warpMat = Imgproc.getPerspectiveTransform(srcAffine, targetAffine);

        Imgproc.warpPerspective(fig5, fig12, warpMat, fig12.size(), Imgproc.INTER_LINEAR, Core.BORDER_TRANSPARENT);

        // Show each image in a window for comparisons
        CVUtils.showWindow("Figure 5", fig5);
        CVUtils.showWindow("Figure 10", fig10);
        CVUtils.showWindow("Figure 12", fig12);

        CVUtils.waitForKeyboardInput();
    }
}

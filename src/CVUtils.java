import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class CVUtils {
    public static void loadOpenCV() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static Mat readImage(String path) {
        return Imgcodecs.imread(path);
    }

    public static void showWindow(String windowTitle, Mat image) {
        HighGui.imshow(windowTitle, image);
    }

    public static void waitForKeyboardInput() {
        // Don't terminate the program until any keyboard key has been pressed while windows are focused
        HighGui.waitKey();

        // Terminate the program once all windows have been closed, without this line the program never ends
        System.exit(0);
    }

    public static double wrap(double x) {
        double min = 0;
        double max = 255;
        if (x > max) {
            while (x > max) {
                x -= (max + 1);
            }
        } else {
            while (x < min) {
                x += (max + 1);
            }
        }
        return x;
    }

    public static double saturate(double x) {
        return x > 255.0 ? 255.0 : (x < 0.0 ? 0.0 : x);
    }

    public static Mat brighten(Mat src, double alpha) {
        Mat result = new Mat(src.size(), src.type());
        src.convertTo(result, -1, alpha);
        return result;
    }

    public static Mat brighten(Mat src, double alpha, double beta) {
        Mat result = new Mat(src.size(), src.type());
        src.convertTo(result, -1, alpha, beta);
        return result;
    }

    public static Mat getTranslationMatrix(int x, int y) {
        Mat translationMatrix = new Mat(2, 3, CvType.CV_32F);
        double[] data = {1, 0, x, 0, 1, y};
        translationMatrix.put(0, 0, data);
        return translationMatrix;
    }

    public static Mat translate(Mat src, int x, int y) {
        Mat dest = new Mat(src.size(), src.type());
        Imgproc.warpAffine(src, dest, getTranslationMatrix(x, y), src.size());
        return dest;
    }

    public static Mat scale(Mat src, Size size) {
        Mat dest = new Mat(size, src.type());
        Imgproc.resize(src, dest, size);
        return dest;
    }

    public static Mat flip(Mat src, int flipCode) {
        Mat dest = new Mat(src.size(), src.type());
        Core.flip(src, dest, flipCode);
        return dest;
    }

    public static Mat blend(Mat src1, Mat src2, double alpha, double beta) {
        Mat dest = new Mat(src1.size(), src1.type());
        Core.addWeighted(src1, alpha, src2, beta, 0.0, dest);
        return dest;
    }

    public static Mat rotate(Mat src, double angle, double scale) {
        Mat dest = new Mat(src.size(), src.type());
        Mat rotationMatrix = Imgproc.getRotationMatrix2D(new Point(src.cols() / 2, src.rows() / 2), angle, scale);
        Imgproc.warpAffine(src, dest, rotationMatrix, src.size());
        return dest;
    }

    public static Mat rotate(Mat src, Point point, double angle, double scale) {
        Mat dest = new Mat();
        Mat rotationMatrix = Imgproc.getRotationMatrix2D(point, angle, scale);
        Imgproc.warpAffine(src, dest, rotationMatrix, src.size());
        return dest;
    }
}

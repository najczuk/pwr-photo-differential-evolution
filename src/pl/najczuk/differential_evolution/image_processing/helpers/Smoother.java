package pl.najczuk.differential_evolution.image_processing.helpers;

import static org.bytedeco.javacpp.opencv_core.IplImage;
import static org.bytedeco.javacpp.opencv_core.cvReleaseImage;
import static org.bytedeco.javacpp.opencv_highgui.cvLoadImage;
import static org.bytedeco.javacpp.opencv_highgui.cvSaveImage;
import static org.bytedeco.javacpp.opencv_imgproc.cvSmooth;

/**
 * User: Adrian
 * Date: 11/24/2014
 * Time: 23:00
 */
public class Smoother {
    public static void smooth(String filename,String filename2) {
        IplImage image = cvLoadImage(filename);
        if (image != null) {
            cvSmooth(image, image);
            cvSaveImage(filename2, image);
            cvReleaseImage(image);
        }
    }


}

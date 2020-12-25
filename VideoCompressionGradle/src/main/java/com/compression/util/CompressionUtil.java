package com.compression.util;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

public class CompressionUtil {

    public static void compressVideo(String source, String destination) {
        try {
            //String basePath = System.getProperty("user.dir");

            OSUtil.OS os = OSUtil.getOS();

            //String sourceFolder = "files";
            String destinationFolder = "converted";

            String ffmpeg = "";
            if (os == OSUtil.OS.WINDOWS) {
                ffmpeg = "ffmpeg.exe";
            } else if (os == OSUtil.OS.LINUX) {
                ffmpeg = "ffmpeg-linux";
            } else if (os == OSUtil.OS.MAC) {
                ffmpeg = "ffmpeg-mac";
            }

            // setting ffmpeg path and file
            ffmpeg = "/Users/hagai.ovadia/Hagai/mydev/VideoCompressionGradle" + File.separator + ffmpeg;
            //source = basePath + File.separator + sourceFolder + File.separator + source;

            File f = new File(source);
            long fileSize = f.length(); //retunrs the file size in bytes
            System.out.format("The size of the file: %d bytes", fileSize);
            String speedUp = "";

            destination = "/Users/hagai.ovadia/Hagai/mydev/VideoCompressionGradle" + File.separator + destinationFolder + File.separator + destination;
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("Conversion started at: " + timestamp);
            System.out.println("Conversion is under process, please wait for time ");
            Runtime rt = Runtime.getRuntime();

            // this is where the conversion takes place
            // -preset ultrafast, superfast, veryfast, faster, fast, medium (the default), slow, slower, veryslow.
            Process process = rt.exec(ffmpeg + " -y -i " + source + " -vcodec h264 -acodec aac  "+speedUp+" " + destination);
            process.waitFor();

        } catch (Exception e) {// | InterruptedException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        String source = "/Users/hagai.ovadia/Desktop/videoforcompression2020.mov";
        String destination = "output.mov";
        long start = System.currentTimeMillis();
        CompressionUtil.compressVideo(source, destination);
        long end = System.currentTimeMillis();
        System.out.println("duration in seconds:" + (end-start)/1000);

    }
}

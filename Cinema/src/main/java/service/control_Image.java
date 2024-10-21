package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class control_Image {
	 private String destinationFolder = "src\\main\\java\\imgs";

	    public control_Image() {
	        File dir = new File(destinationFolder);
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }
	    }
	    
	 // Phương thức copy ảnh và trả về tên các ảnh đã sao chép
	    public String copyImages() {
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setMultiSelectionEnabled(true);
	        int result = fileChooser.showOpenDialog(null);

	        if (result == JFileChooser.APPROVE_OPTION) {
	            File[] selectedFiles = fileChooser.getSelectedFiles();
	            StringBuilder imageNames = new StringBuilder();

	            try {
	                Arrays.stream(selectedFiles).forEach(file -> {
	                    String imageName = copyImage(file); // Sao chép ảnh và lấy tên
	                    imageNames.append(imageName).append(", "); // Nối tên ảnh vào chuỗi
	                });


	            } catch (Exception e) {
	                e.printStackTrace();
	                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi sao chép ảnh.");
	            }

	            // Xóa dấu phẩy cuối cùng nếu có ảnh đã sao chép
	            if (imageNames.length() > 0) {
	                imageNames.setLength(imageNames.length() - 2); // Xóa ", " cuối cùng
	            }

	            return imageNames.toString(); // Trả về chuỗi các tên ảnh đã sao chép
	        }
	        return ""; // Trả về chuỗi rỗng nếu không có ảnh nào được chọn
	    }

	    // Phương thức sao chép từng ảnh vào thư mục
	    private String copyImage(File selectedFile) {
	    	String nameIMG = null;
	        try {
	            Path sourcePath = selectedFile.toPath();
	            Path destinationPath = Paths.get(destinationFolder +"/"+ selectedFile.getName());
	            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

	            // Lưu tên ảnh vào cơ sở dữ liệu
	            nameIMG = selectedFile.getName();
//	            System.out.println(nameIMG);
////	            
//	            System.out.println(destinationFolder);
//	            saveImageNameToDatabase(0, nameIMG);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return nameIMG;
	    }
	    public static void main(String[] args) {
			control_Image i = new control_Image();
			i.copyImages();
		}

}

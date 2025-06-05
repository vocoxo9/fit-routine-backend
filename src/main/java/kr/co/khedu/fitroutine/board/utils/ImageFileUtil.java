package kr.co.khedu.fitroutine.board.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;

public class ImageFileUtil {

    /**
     * 업로드되는 파일의 이름을 변경하여 전달된 경로에 저장
     * 	* 변경되는 파일명 형식 : spring_{현재날짜시간}{랜덤값}.{확장자}
     * @param file		업로드되는 파일정보
     * @param session	물리적 경로를 얻기 위해 사용되는 객체
     * @param path		저장할 경로
     * @return			변경된 파일명
     */
    public static String saveFile(MultipartFile file, HttpSession session, String path) {

        String originName = file.getOriginalFilename();
        // => ex) test.jpg , test.docx , test.txt , test.20250410.txt
        String ext = originName.substring( originName.lastIndexOf(".") );

        String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        int random = (int)(Math.random() * (99999-10000)) + 10000;

        String changeName = "spring_" + now + random + ext;

        // 물리적 경로 조회 --> 전달된 저장할 경로 기준으로 조회
        String savePath = session.getServletContext().getRealPath(path);
        try {
            File folder = new File(savePath);
            if (!folder.exists()) {
                System.out.println("파일 저장 경로가 존재하지 않아 생성!::" + savePath);
                folder.mkdirs();
            }
            file.transferTo(new File(savePath + changeName));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return path + changeName;
    }
}

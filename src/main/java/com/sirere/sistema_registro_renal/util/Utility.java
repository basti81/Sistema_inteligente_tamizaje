package com.sirere.sistema_registro_renal.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Utility {
    public static String saveFile(MultipartFile multiPart, String ruta) {
        // Nombre del archivo
        String originalFilename = multiPart.getOriginalFilename();
        try {
            // Formamos el nombre del archivo para guardarlo en el disco duro.
            File imageFile = new File(ruta + originalFilename);
            System.out.println("Archivo: " + imageFile.getAbsolutePath());
            //Guardamos fisicamente el archivo en HD.
            multiPart.transferTo(imageFile);
            return originalFilename;
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }

}

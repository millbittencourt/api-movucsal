package com.milleddy.movucsal.controller;

import com.milleddy.movucsal.entity.Caminho;
import com.milleddy.movucsal.entity.Ponto;
import com.milleddy.movucsal.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CsvController {

    private CsvService csvService;

    @Autowired
    public CsvController(CsvService csvService){
        this.csvService = csvService;
    }

    @PostMapping("/upload-ponto-csv")
    ResponseEntity<Ponto> uploadPontoCSVFile(@RequestParam("pontos") MultipartFile file ) {
        try {
            return new ResponseEntity(csvService.readPontoCsvFile(file), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @PostMapping("/upload-caminho-csv")
    ResponseEntity<Caminho> uploadCaminhoCSVFile(@RequestParam("caminho") MultipartFile file ) {
        try {
            return new ResponseEntity(csvService.readCaminhoCsvFile(file), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @GetMapping("/teste")
    List<String> returnOk() {
        try {
            List<String> tes = new ArrayList<>();
            tes.add("You did it! It's alive =D");

            return tes;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY, e.getMessage());
        }
    }

}

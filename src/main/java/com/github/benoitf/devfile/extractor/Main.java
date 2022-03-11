package com.github.benoitf.devfile.extractor;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import com.github.benoitf.devfile.extractor.entity.DevfileImpl;

public class Main {

    public static void main(String[] args) {

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("devfile-persistence-unit");
            EntityManager em = emf.createEntityManager();
            // grab the devfiles
            List<DevfileImpl> devfileImplList = em.createQuery("SELECT d FROM Devfile d", DevfileImpl.class)
                    .getResultList();

            Files.createDirectories(Paths.get("/tmp/devfiles"));
            for (DevfileImpl devfile : devfileImplList) {
                var id = devfile.getId();
                ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                mapper.setSerializationInclusion(Include.NON_NULL);
                mapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false);
                var yamlContent = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(devfile);
                // write into a given file the devfile yaml
                var fileWriter = new FileWriter("/tmp/devfiles/" + id + ".yaml");
                fileWriter.write(yamlContent);
                fileWriter.close();
            }
            System.out.println("All devfiles written successfully");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
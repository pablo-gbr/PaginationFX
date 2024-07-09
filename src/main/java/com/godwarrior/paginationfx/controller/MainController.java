package com.godwarrior.paginationfx.controller;

import com.godwarrior.paginationfx.database.mysql.ConnectionMSQL;
import com.godwarrior.paginationfx.models.Filter;
import com.godwarrior.paginationfx.models.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;

public class MainController {
    @FXML
    private VBox vboxConteiner;

    @FXML
    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/godwarrior/paginationfx/resources/view/PaginationTableView.fxml"));
            Node paginationTable = loader.load();

            ConnectionMSQL.getInstance("localhost", "paginationtest", "root", "");

            PaginationTableController newTable = loader.getController();
            newTable.initialize(Usuario.class, "usuario");

            newTable.addColumn("Identificador", "id");
            newTable.addColumn("Nombre", "nombre");
            newTable.addColumn("Apellido", "apellido");
            newTable.addColumn("Telefono", "telefono");
            newTable.addColumn("Fecha Nacimiento", "fechaNacimiento");
            newTable.addColumn("¿Esta activo?", "activo");
            newTable.addColumn("Hora de Registro", "horaRegistro");


            newTable.addFilters(new ArrayList<>(Arrays.asList(
                    new Filter("Id de Usuario", "id", "number"),
                    new Filter("Nombre de Usuario", "nombre", "text"),
                    new Filter("Telefono de Usuario", "telefono", "number"),
                    new Filter("¿Esta activo?", "activo", "bool")

            )));

            VBox.setVgrow(paginationTable, Priority.ALWAYS);
            vboxConteiner.getChildren().add(paginationTable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
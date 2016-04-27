/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysabynin.molecule.controller;

import javafx.collections.FXCollections;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import org.jmol.adapter.smarter.SmarterJmolAdapter;
import org.jmol.api.JmolViewer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Stream;

/**
 * FXML Controller class
 *
 * @author kseniadiogenova
 */
public class VisualizerController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private Pane jmolContentPane;

    @FXML
    private ListView moleculesListView;

    private JmolPanel jmolPanel;

    @FXML
    public void handleFileDialog(MouseEvent arg0) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select CML files");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CML", "*.cml")
            );

            File file = fileChooser.showOpenDialog(null);

            openCMLFile(file.getPath());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        String[] names = Stream.of(Molecule.values()).map(Molecule::getText).toArray(String[]::new);
        moleculesListView.setItems(FXCollections.observableArrayList(names));

        moleculesListView.getSelectionModel().selectedItemProperty()
                .addListener((selected, oldMolecule, newMolecule) -> {
                    if (newMolecule != null) {
                        String moleculeText = String.valueOf(newMolecule);

                        Molecule molecule = Stream.of(Molecule.values())
                                .filter(e -> moleculeText.equals(e.getText()))
                                .findFirst().get();

                        openCMLFile(getClass()
                                .getResource("/molecule/" + molecule.getFileName() + ".cml").toExternalForm());

                        jmolPanel.viewer.evalString("select all;label %e;");
                    }
                });

        renderMolecule(Molecule.WATER.getFileName());
    }

    private void openCMLFile(String path){
        jmolPanel.viewer.openFile(path);
        jmolPanel.viewer.evalString("select all;label %e;");
    }

    private void renderMolecule(String name) {
        final SwingNode swingNode = new SwingNode();

        SwingUtilities.invokeLater(() -> {
            jmolPanel = new JmolPanel();
            jmolPanel.viewer.evalString("forceAutobond=true;");
            jmolPanel.setPreferredSize(new Dimension(650, 400));
            if (name != null)
                openCMLFile(getClass().getResource("/molecule/" + name + ".cml").toExternalForm());
            jmolPanel.viewer.evalString("select all;label %e;");
            swingNode.setContent(jmolPanel);
        });

        jmolContentPane.getChildren().add(swingNode);
    }

    static class JmolPanel extends JPanel {

        JmolViewer viewer;

        private final Dimension currentSize = new Dimension();

        JmolPanel() {
            viewer = JmolViewer.allocateViewer(this, new SmarterJmolAdapter(),
                    null, null, null, null, null);
        }

        @Override
        public void paint(Graphics g) {
            getSize(currentSize);
            viewer.renderScreenImage(g, currentSize.width, currentSize.height);
        }
    }
}

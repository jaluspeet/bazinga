package it.unibo.pss.controller;

import it.unibo.pss.common.SharedConstants;
import it.unibo.pss.controller.observer.ModelDTO;
import it.unibo.pss.controller.observer.ModelObserver;
import it.unibo.pss.model.Model;
import it.unibo.pss.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Controller extends Application implements ModelObserver {

	private Model model;
	private View view;
	private ModelDTO modelDTO;

	/* start the application */
	@Override
	public void start(Stage stage) {

		// model initialization
		this.model = new Model(SharedConstants.WORLD_WIDTH, SharedConstants.WORLD_HEIGHT);
		this.modelDTO = new ModelDTO(this.model.getGrid());
		this.model.addObserver(this);

		// view initialization
		this.view = new View(stage, SharedConstants.WINDOW_TITLE, SharedConstants.WINDOW_WIDTH, SharedConstants.WINDOW_HEIGHT, modelDTO);
	}

	/* notify the view to update the model data */
	@Override
	public void onModelUpdated() {
		this.modelDTO = new ModelDTO(this.model.getGrid());
		this.view.updateModel(modelDTO);
	}

	/* Returns the latest ModelDTO from the model. */
	public ModelDTO getLatestModelDTO() {
		return new ModelDTO(model.getGrid());
	}

	/* main method */
	public static void main(String[] args) {
		launch(args);
	}
}

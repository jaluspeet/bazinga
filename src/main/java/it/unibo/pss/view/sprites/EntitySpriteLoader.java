package it.unibo.pss.view.sprites;

import it.unibo.pss.model.entity.BasicEntity;
import it.unibo.pss.model.entity.PlantEntity;
import it.unibo.pss.model.entity.PreyEntity;
import it.unibo.pss.model.entity.PredatorEntity;
import javafx.scene.image.Image;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.IdentityHashMap;
import java.util.Random;

/** Loads and caches entity sprites from a given path. **/
public class EntitySpriteLoader {
	private final List<Image> predatorTextures;
	private final List<Image> preyTextures;
	private final List<Image> plantTextures;
	private final Map<BasicEntity, Image> cache = new IdentityHashMap<>();
	private final Random random = new Random();

	public EntitySpriteLoader(String basePath) {
		predatorTextures = loadTextures(basePath, "predator");
		preyTextures = loadTextures(basePath, "prey");
		plantTextures = loadTextures(basePath, "plant");
	}

	private List<Image> loadTextures(String basePath, String prefix) {
		List<Image> textures = new ArrayList<>();
		int index = 0;
		while (true) {
			String imagePath = basePath + "/" + prefix + "_" + index + ".png";
			try {
				Image img = new Image(getClass().getResourceAsStream(imagePath));
				if (img.getWidth() == 0)
					break;
				textures.add(img);
				index++;
			} catch (Exception e) {
				break;
			}
		}
		return textures;
	}

	public Image getEntityTexture(BasicEntity entity) {
		if (cache.containsKey(entity)) {
			return cache.get(entity);
		}
		Image texture = null;
		if (entity instanceof PredatorEntity) {
			texture = getRandomTexture(predatorTextures);
		} else if (entity instanceof PreyEntity) {
			texture = getRandomTexture(preyTextures);
		} else if (entity instanceof PlantEntity) {
			texture = getRandomTexture(plantTextures);
		}
		cache.put(entity, texture);
		return texture;
	}

	private Image getRandomTexture(List<Image> textures) {
		if (textures.isEmpty())
			return null;
		return textures.get(random.nextInt(textures.size()));
	}
}

package org.orienteer.core.component.visualizer;

import java.io.Serializable;
import java.util.Collection;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.io.IClusterable;
import org.orienteer.core.component.property.DisplayMode;

import com.orientechnologies.orient.core.metadata.schema.OProperty;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * Interface of Orienteer's visualizers
 */
public interface IVisualizer extends IClusterable
{
	public static final String DEFAULT_VISUALIZER = "default";
	public String getName();
	public boolean isExtended();
	public Collection<OType> getSupportedTypes();
	public <V> Component createComponent(String id, DisplayMode mode, IModel<ODocument> documentModel, IModel<OProperty> propertyModel, IModel<V> valueModel);
}

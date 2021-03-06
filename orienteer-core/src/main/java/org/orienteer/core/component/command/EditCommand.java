package org.orienteer.core.component.command;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.orienteer.core.component.BootstrapType;
import org.orienteer.core.component.FAIconType;
import org.orienteer.core.component.property.DisplayMode;
import org.orienteer.core.component.structuretable.OrienteerStructureTable;
import org.orienteer.core.component.structuretable.StructureTableCommandsToolbar;
import org.orienteer.core.component.table.DataTableCommandsToolbar;
import org.orienteer.core.component.table.OrienteerDataTable;

/**
 * {@link AjaxCommand} which trigger display mode
 *
 * @param <T> the type of an entity to which this command can be applied
 */
public class EditCommand<T> extends AjaxCommand<T>
{
	private static final long serialVersionUID = 1L;
	private IModel<DisplayMode> displayModeModel;
	
	public EditCommand(OrienteerDataTable<T, ?> table, IModel<DisplayMode> displayModeModel)
	{
		super(new ResourceModel("command.edit"), table);
		this.displayModeModel = displayModeModel;
	}

	public EditCommand(OrienteerStructureTable<T, ?> structureTable, IModel<DisplayMode> displayModeModel)
	{
		super(new ResourceModel("command.edit"), structureTable);
		this.displayModeModel = displayModeModel;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		setIcon(FAIconType.edit);
		setBootstrapType(BootstrapType.PRIMARY);
		setChangingDisplayMode(true);
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();
		setVisible(!DisplayMode.EDIT.equals(displayModeModel.getObject()));
	}

	@Override
	public void onClick(AjaxRequestTarget target) {
		displayModeModel.setObject(DisplayMode.EDIT);
		target.add(this);
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		if(displayModeModel!=null) displayModeModel.detach();
	}
	
	

}

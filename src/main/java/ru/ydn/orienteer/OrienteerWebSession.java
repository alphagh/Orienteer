package ru.ydn.orienteer;

import org.apache.wicket.Session;
import org.apache.wicket.request.Request;

import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.record.impl.ODocument;

import ru.ydn.orienteer.modules.PerspectivesModule;
import ru.ydn.wicket.wicketorientdb.OrientDbWebSession;

public class OrienteerWebSession extends OrientDbWebSession
{
	private OIdentifiable perspective;
	
	public OrienteerWebSession(Request request)
	{
		super(request);
	}
	
	public static OrienteerWebSession get()
	{
		return (OrienteerWebSession)Session.get();
	}
	
	public OrienteerWebSession setPerspecive(ODocument perspective)
	{
		this.perspective = perspective;
		return this;
	}
	
	public ODocument getPerspective()
	{
		if(perspective instanceof ODocument)
		{
			return (ODocument) perspective;
		}
		else
		{
			if(perspective!=null)
			{
				perspective = perspective.getRecord();
				return (ODocument)perspective;
			}
			else
			{
				OrienteerWebApplication app = OrienteerWebApplication.get();
				PerspectivesModule perspectiveModule = app.getServiceInstance(PerspectivesModule.class);
				perspective = perspectiveModule.getDefaultPerspective(getDatabase());
				return (ODocument)perspective;
			}
			
		}
	}

	@Override
	public void detach() {
		if(perspective!=null) perspective = perspective.getIdentity();
		super.detach();
	}
	
	
	

}

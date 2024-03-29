package it.nexus.webcontrol.views.freemaker.tags;

import com.opensymphony.xwork2.util.ValueStack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NexusModels {
  protected TitleModel title;
  protected DateInputModel dateinput;
  protected ChoiceBoxModel choicebox;
  protected LayoutPanelModel layoutpanel;


  private ValueStack stack;
  private HttpServletRequest req;
  private HttpServletResponse res;

  public NexusModels(ValueStack stack, HttpServletRequest req,
                     HttpServletResponse res) {
    this.stack = stack;
    this.req = req;
    this.res = res;
  }

  public TitleModel getAnchor() {
    if (title == null) {
      title = new TitleModel(stack, req, res);
    }
    if (dateinput == null) {
      dateinput = new DateInputModel(stack, req, res);
    }
    if (choicebox == null) {
      choicebox = new ChoiceBoxModel(stack, req, res);
    }

    if (layoutpanel == null)
      layoutpanel = new LayoutPanelModel(stack, req, res);
    return title;
  }
}

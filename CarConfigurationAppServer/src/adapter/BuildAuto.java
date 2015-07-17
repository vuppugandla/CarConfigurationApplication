/*
 * This class is a dummy class which is exposed to deal with operations. It implements interfaces CreateAuto, UpdateAuto, DeleteAuto and extends abstract class proxyAutomobile
 * Author: Vignan Uppugandla
 */
package adapter;

import java.io.Serializable;

import scale.IEditOptions;
import server.AutoServer;

public class BuildAuto extends ProxyAutomobile implements CreateAuto, UpdateAuto, DeleteAuto, FixAuto, IEditOptions, Serializable, AutoServer{
	private static final long serialVersionUID = 1L;

}

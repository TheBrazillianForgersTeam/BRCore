package cf.brforgers.core.plugin;

import cpw.mods.fml.relauncher.FMLInjectionData;
import cpw.mods.fml.relauncher.IFMLCallHook;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Map;

//import net.minecraftforge.fml.relauncher.CoreModManager;
//
//import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;
//import net.minecraftforge.fml.common.versioning.VersionParser;
//import net.minecraftforge.fml.relauncher.FMLInjectionData;
//import net.minecraftforge.fml.relauncher.IFMLCallHook;
//import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
//import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@TransformerExclusions(value = {"cf.brforgers.core.plugin"})
public class BRCorePlugin implements IFMLLoadingPlugin, IFMLCallHook
{
    public static final String mcVersion = "[1.7.10]";
    public static final String version = "${mod_version}";

    public static File minecraftDir;
    public static String currentMcVersion;
    public static Logger logger = LogManager.getLogger("BRFoundationCore");

    public BRCorePlugin() {
        if (minecraftDir != null)
            return;//get called twice, once for IFMLCallHook

        minecraftDir = (File) FMLInjectionData.data()[6];
        currentMcVersion = (String) FMLInjectionData.data()[4];

        
        
        DepLoader.load();
    }

	@Override
	public Void call() throws Exception {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return null;
	}

	@Override
	public String getModContainerClass() {
		return "cf.brforgers.core.BRCore";
	}

    @Override
    public String getSetupClass() {
        return getClass().getName();
    }

	@Override
	public void injectData(Map<String, Object> data) {
		
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}
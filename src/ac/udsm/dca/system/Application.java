package ac.udsm.dca.system;

import ac.udsm.dca.core.BaseAgent;
import ac.udsm.dca.exception.InvalidNodeTypeException;
import jade.Boot;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application {
    private final Configuration configuration;
    private String netIp;
    private String agentClass;

    public Application(String... args) {
        if (args.length == 0) {
            configuration = new Configuration();
        } else {
            configuration = new Configuration(args);
        }
    }

    private void initNetwork() throws UnknownHostException {
        netIp = Inet4Address.getLocalHost().getHostAddress();
        Log.d("node ip :" + netIp);
    }

    private void initAgent() throws InvalidNodeTypeException {
        switch (configuration.getType()) {
            case BaseAgent.TYPE_GRID:
                agentClass = "ac.udsm.dca.agents.GridAgent";
                break;
            case BaseAgent.TYPE_LOAD:
                agentClass = "ac.udsm.dca.agents.LoadAgent";
                break;
            default:
                throw new InvalidNodeTypeException("Invalid node type");
        }
    }

    private void runJade() {
        List<String> params = new ArrayList<>();
        if (configuration.isGui()) {
            params.add("-gui");
        }
        params.add("-host");
        params.add(configuration.getHost());
        if (configuration.getType() == BaseAgent.TYPE_CONTAINER) {
            params.add("-name");
            params.add((String) configuration.getProperty("name", "System"));
        } else {
            params.add("-container");
        }
        if (agentClass != null) {
            Random r = new Random();
            r.setSeed(System.currentTimeMillis());
            params.add(agentClass + "_" + r.nextInt() + ":" + agentClass);
        } else {
            params.add("Chrono:ac.udsm.dca.core.Chronometer;");
        }
        Boot.main(params.toArray(new String[]{}));
    }

    public int run() {
        if (configuration.isLoaded()) {
            Log.d("node id is :" + configuration.getId());
            Log.d("node type is :" + configuration.getType());
            try {
                initNetwork();
                if (configuration.getType() != 0) {
                    initAgent();
                }
                runJade();
            } catch (UnknownHostException | InvalidNodeTypeException e) {
                Log.e(e.getMessage());
            }
            return 0;
        }
        return 1;
    }

}

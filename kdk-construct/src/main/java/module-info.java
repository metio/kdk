module wtf.metio.kdk.construct {

    requires static org.immutables.value;
    requires org.yaml.snakeyaml;

    exports wtf.metio.kdk.construct.config;
    exports wtf.metio.kdk.construct.meta;
    exports wtf.metio.kdk.construct.service;
    exports wtf.metio.kdk.construct.workloads;
    exports wtf.metio.kdk.serialize;
    exports wtf.metio.kdk.stack;
    exports wtf.metio.kdk.validate;
    exports wtf.metio.kdk.validate.config;
    exports wtf.metio.kdk.validate.meta;
    exports wtf.metio.kdk.validate.service;
    exports wtf.metio.kdk.validate.workloads;

}

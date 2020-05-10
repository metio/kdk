var service = Service.builder().spec(ServiceSpec.builder().build()).build();

var serviceYaml = Serialize.asYaml(service);

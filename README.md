# k8s definition kit (kdk)

`kdk` is an infrastructure as code approach for Kubernetes inspired by [aws-cdk](https://docs.aws.amazon.com/cdk/latest/guide/home.html) and [cdk-clj](https://github.com/StediInc/cdk-clj). Think of it like a programmable [helm](https://helm.sh/) chart.

## Alternatives

- https://cdk8s.io/

## Usage

`kdk` requires at least Java 11 installed and supports 4 major use cases:

1. **construct** Kubernetes resources
2. **stack** Kubernetes resources
3. **validate** Kubernetes resources
4. **serialize** Kubernetes resources

### Construct

Use `kdk` to construct various k8s resources like this:

```
var service = Service.builder()
    .metadata(...)
    .spec(...)
    .build();
```

Each supported k8s resource has a corresponding Java class with the same name that expose one or more builder methods. Use those methods to construct your stack.

### Stack

The `Stack` class does not exist in the k8s API and solely exists in `kdk` to wrap multiple resources together in one 'stack'.

```
var stack = Stack.builder()
    .addServices(service)
    .build();
```

Consider publishing your application stack, so that others can easily integrate your work in their project.

### Validate

In order to validate your construct, use the `Validate` class. It supports an easy to use API which can be customizationed in order to fit to your project. Several built-in validators are available, as well as presets which group these validators together.

```
var errors = Validate.validate(stack);
```

The above call will use the `recommended` preset. Use another one by specifying the validators as a second parameter:

```
var errors = Validate.validate(stack, Validators.all());
```

Besides `recommended`, there are `all`, `deprecated`, and `none` presets. Use `Validators.builder()` to construct your custom set of validation rules. Each validator is a function that transforms `TYPE` to `Stream<ValidationError`. `TYPE` represents one of the available k8s API types. The builder for `Validators` will accept any function that matches that signature as a custom validator and execute it in case a matching resource is encountered during validation.

```
var errors = Validate.validate(stack, Validators.builder()
    .addDeploymentSpec(deploymentSpec -> Stream.of(ValidationError.of("some-error-code", deploymentSpec)))
    .build());
```

If you rather want to start from a predefined preset and extend it with additional validators, use this:

```
Validators.from(Validators.recommended())
    .withDeploymentSpec(deploymentSpec -> Stream.of(ValidationError.of("some-error-code", deploymentSpec)));
```

#### Validation Errors & Presets

| ID                                         | Resource   | Presets          | Description                              | Fix                                             |
|--------------------------------------------|------------|------------------|------------------------------------------|-------------------------------------------------|
| urn:kdk:error:secret:missing-metadata      | Secret     | all, recommended | Resource is missing the 'metadata' field | Add a 'metadata' field                          |
| urn:kdk:error:ingress:missing-metadata     | Ingress    | all, recommended | Resource is missing the 'metadata' field | Add a 'metadata' field                          |
| urn:kdk:error:service:missing-metadata     | Service    | all, recommended | Resource is missing the 'metadata' field | Add a 'metadata' field                          |
| urn:kdk:error:deployment:missing-metadata  | Deployment | all, recommended | Resource is missing the 'metadata' field | Add a 'metadata' field                          |
| urn:kdk:error:job:missing-metadata         | Job        | all, recommended | Resource is missing the 'metadata' field | Add a 'metadata' field                          |
| urn:kdk:error:annotation:key-blank         | Annotation | all, recommended | The 'key' field is blank                 | Change the 'key' field to something non-blank   |
| urn:kdk:error:annotation:value-blank       | Annotation | all, recommended | The 'value' field is blank               | Change the 'value' field to something non-blank |
| urn:kdk:error:objectMeta:name-missing      | ObjectMeta | all, recommended | The 'name' field is not set              | Add a 'name' field                              |
| urn:kdk:error:objectMeta:namespace-missing | ObjectMeta | all              | The 'namespace' field is not set         | Add a 'namespace' field                         |

### Serialize

In order to serialize your construct to YAML, use the `Serialize` class.

```
var yaml = Serialize.asYaml(stack);
```

Use the generated YAML string and write it into a file or just output it to the console.

## Integration

There are several approaches to integrate `kdk` in your project.

### Static-Void-Main-Approach

Java applications can be started with `java <your.main.Class>`. The specified class has to declare a static method that returns noting (`void`), is called `main`, and accepts an array of strings as its input. The following code shows an example:

```java
package com.example;

import wtf.metio.kdk.construct.config.*;
import wtf.metio.kdk.construct.meta.*;
import wtf.metio.kdk.construct.service.*;
import wtf.metio.kdk.construct.workloads.*;
import wtf.metio.kdk.serialize.*;
import wtf.metio.kdk.stack.*;
import wtf.metio.kdk.validate.*;

public class ExampleApplication {

    public static void main(String[] arguments) {
        var name = "test";
        var host = "example.tld";
        var path = "/service";
        var port = ServicePort.of(2002);
        var label = Label.of("app", name);

        var metadata = ObjectMeta.builder()
          .name(name)
          .namespace("example")
          .addLabels(label)
          .build();
        var service = Service.of(
          metadata,
          ServiceSpec.of(port, Selector.of(label)));
        var ingress = Ingress.of(
          metadata,
          IngressSpec.builder()
              .addRules(IngressRule.of(host, HTTPIngressRuleValue.builder()
                  .addPaths(HTTPIngressPath.of(IngressBackend.of(name, port), path))
                  .build()))
              .addTls(IngressTLS.of("tls-secret", host))
              .build());

        var stack = Stack.builder()
            .addService(service)
            .addIngress(ingress)
            .build();

        var errors = Validate.validate(stack);

        if (errors.isEmpty()) {
            var yaml = Serialize.asYaml(stack);
            System.out.print(yaml);
        }
    }

}
```

### JShell-Approach

[JShell](https://docs.oracle.com/javase/9/jshell/introduction-jshell.htm) provides a [REPL](https://en.wikipedia.org/wiki/Read%E2%80%93eval%E2%80%93print_loop) for interactive Java development.

You will need both `kdk` and `snakeyml` (for serialization) on your classpath. You can use the [`jshell` artifact](https://codeberg.org/attachments/a8c30837-4afb-4cde-a5e0-c2bfca914782) which already includes all required files, and a bootstrap file to set up the classpath and load all `kdk` classes. Its [README](./kdk-construct/src/main/jshell/README.md) file explains how to start a new `jshell` session with `kdk`.

## License

To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide.
This software is distributed without any warranty.

You should have received a copy of the CC0 Public Domain Dedication along with this software.
If not, see http://creativecommons.org/publicdomain/zero/1.0/.

## Mirrors

`ilo` is mirrored across several git repositories.
Use any of the following to get a copy of the source.

- https://codeberg.org/metio.wtf/kdk
- https://github.com/metio.wtf/kdk (master only)
- https://gitlab.com/metio.wtf/kdk (master only)
- https://bitbucket.org/metio-wtf/kdk (master only)

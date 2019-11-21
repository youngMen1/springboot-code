## Swagger Butler
当我们构建分布式系统的时候，虽然我们可以用Swagger来方便为每个服务自动产出API文档页面。
但是随着服务数量的增多，内部服务间的依赖关系的复杂度增加，
每个服务开发人员要关心和查阅的文档变得越来越多。由于每个服务的文档地址可能都不一样，
这使得不得不维护一个文档的索引来方便查阅，并且这个索引还需要不断的去维护更新。
那么有没有什么工具可以帮我们快速的汇集这些服务的Swagger文档呢？只需要记住一个访问地址，
就可以访问所有服务的API文档？当服务增加的时候，不需要手工维护就能自动发现新服务的API文档？
如果您有上面的这些问题，那么不妨看看这个项目，或许可以解决您的这些烦恼！
Swagger Butler是一个基于Swagger与Zuul构建的API文档汇集工具。通过构建一个简单的Spring Boot应用，
增加一些配置就能将现有整合了Swagger的Web应用的API文档都汇总到一起，方便查看与测试。

## 项目地址
Github：https://github.com/dyc87112/swagger-butler
Gitee：https://gitee.com/didispace/swagger-butler
# 🚀 他来了，为大模型量身定制的响应式编程范式（1） —— 从接入 DeepSeek 开始吧 🚀

哒哒哒，他来了！👋 今天我们要介绍一种新型的 Java 响应式大模型编程范式 —— **FEL**。你可能听说过 langchain，那么你暂且可以把 FEL 看作是 Java 版本的 langchain。😎 话不多说，今天我们就从接入当前热门的 **DeepSeek** 开始，带大家认识一下 FEL。通过 FEL，你可以轻松实现大模型应用的编排和运行，开启智能编程的新篇章！🎉

## 🛠️ 快速上手：轻松接入 DeepSeek

### 1. 准备环境

首先，进入老生常谈的环节 —— 准备环境。进入 [FIT-Framework 项目地址](https://github.com/ModelEngine-Group/fit-framework)，下载项目代码。根据[入门指南](https://github.com/ModelEngine-Group/fit-framework)，你可以快速部署 FIT 环境，并参考 FEL 的指导手册，掌握 FEL 模块的强大功能。FEL 模块不仅支持 DeepSeek 的接入，还能支持任何符合 OpenAI API 标准的大模型。此外，它还提供了丰富的工具和大模型操作原语，帮助你快速构建智能应用。🛠️

以下示例地址为 [FEL 示例：01-model](https://github.com/ModelEngine-Group/fit-framework/tree/main/examples/fel-example/01-model)，你可以通过该代码快速上手。具体细节可以参考 [FEL 指导手册：聊天模型使用](https://github.com/ModelEngine-Group/fit-framework/blob/main/docs/framework/fel/java/quick-start-guide/01.%20%E6%A8%A1%E5%9E%8B.md)。

### 2. 启动运行

#### 关键代码

这里我们使用 FEL 最基础的大模型接入能力来接入 DeepSeek。通过 `ChatModel` 的默认实现，你可以指定大模型进行调用。这里展示下示例中关于普通调用和流式调用的关键代码。

普通调用：

```java
public ChatMessage chat(@RequestParam("query") String query) {
    ChatOption option = ChatOption.custom().model(this.modelName).stream(false).build();
    return this.chatModel.generate(ChatMessages.from(new HumanMessage(query)), option).first().block().get();
}
```

流式调用：

```java
public Choir<ChatMessage> chatStream(@RequestParam("query") String query) {
    ChatOption option = ChatOption.custom().model(this.modelName).stream(true).build();
    return this.chatModel.generate(ChatMessages.from(new HumanMessage(query)), option);
}
```

> 1. **ChatOption** 中指定要调用的大模型名称和是否流式返回的标识（设置为 `true` 则表示是通过流式方式获取结果）。
> 2. 然后调用 `generate` 方法开始一次调用。返回的结果是一个响应式流对象，可以通过它来获取执行结果。
> 3. 至此，一个简单的接入代码就写完了！🎉

#### 配置 DeepSeek

在示例的配置文件 `resources/application.yml` 中，配置你的 DeepSeek API 密钥、API 地址以及模型名称。示例配置如下：

> 这里大家可以使用硅基流动平台，上面有一定的免费额度可供大家使用，注册账号后创建`API密钥`。这里的配置示例中配置的就是其平台的信息，其中将`api-key`替换为你创建的`API密钥`即可。

```yaml
fel:
  openai:
    api-base: 'https://api.siliconflow.cn/v1'
    api-key: 'your-api-key'
example:
  model: 'deepseek-ai/DeepSeek-R1'
```

#### 启动程序

1. 配置完成后，就可以启动你的应用程序啦。这里可以通过 `IDEA` 直接启动 DemeApplication
2. 当控制台看到如下信息时，则表明你已经启动成功。

    ```bash
    Start netty http server successfully.
    ```

#### 体验你的成果

##### 普通调用

在浏览器中输入示例请求地址，例如：`http://localhost:8080/ai/example/chat?query=你好，DeepSeek`。你可能看到类似如下的响应：

``` json
{
   "content": "<think>\n\n</think>\n\n你好！我是DeepSeek-R1，一个由深度求索公司开发的智能助手，我会尽我所能为你提供帮助。请问有什么可以为你服务的？",
   "toolCalls": []
}
```

##### 流式调用

在浏览器中输入示例请求地址，例如：`http://localhost:8080/ai/example/chat-stream?query=你好，DeepSeek`。你可能看到类似如下的响应：

``` json
data:{"content":"<think>","toolCalls":[]}

data:{"content":"\n\n","toolCalls":[]}

data:{"content":"</think>","toolCalls":[]}

data:{"content":"\n\n","toolCalls":[]}

data:{"content":"你好","toolCalls":[]}

data:{"content":"！","toolCalls":[]}

data:{"content":"我是","toolCalls":[]}

data:{"content":"Deep","toolCalls":[]}

data:{"content":"Se","toolCalls":[]}

data:{"content":"ek","toolCalls":[]}

...
```

## 🌟 FEL 框架有哪些优点？

上面这个示例还只是我们最基本的功能哦！我们还有更加炫酷的使用方式等你来探索。当然，接下来的时间里，我们会陆续编写一系列文章来让你更加了解我们。😉

### 1. **直观的编排方式**

FEL 框架提供了直观的编排方式，帮助开发者轻松构建复杂的应用逻辑。无论是简单的对话系统，还是复杂的多任务处理，FEL 都能通过简洁的配置和代码，实现高效的应用编排。

比如，一个包含知识库、大模型的大模型应用编排代码可能如下：

```java
AiProcessFlow<String, String> smartAssistantFlow = AiFlows.<String>create()
    .map(query -> Tip.from("query", query)) // 将用户输入转换为内部格式
    .retrieve(new DefaultVectorRetriever(vectorStore)) // 检索相关信息
    .generate(new ChatFlowModel(chatModel, chatOption)) // 调用大模型生成回答
    .format(new JsonOutputParser(serializer, Response.class)) // 格式化输出
    .close();
```

### 2. **丰富的大模型操作原语**

FEL 框架内置了丰富的大模型相关操作原语，包括：

- **RAG检索（retrieve）**：从海量数据中快速提取相关信息。
- **提示词模板（prompt）**：通过预定义的模板，快速生成结构化的输出。
- **大模型接入（generate）**：无缝接入 DeepSeek 等大模型，实现智能对话和生成。
- **记忆（memory）**：支持多轮对话的记忆功能，提升用户体验。
- **Agent（delegate）**：通过智能体实现复杂的任务分解与执行。

这些操作原语为开发者提供了强大的整合串联能力，另外还有一些通用的流操作原语等。有了这些，就能够帮助你轻松应对各种智能应用场景啦！🚀

### 3. **灵活的扩展性**

FEL 框架设计灵活，我们的多种原语操作都是基于接口设计（这个也是我们 FIT 编程框架的核心思想哦），如果你有自定义的一些功能，你可以轻松集成，打造你的专属智能应用。🛠️

## 🌈 未来展望：智能应用的无限可能

我们相信，FEL 框架将成为你探索智能世界的得力助手。通过不断的技术创新和优化，我们将持续拓展框架的功能，提供更加易用和丰富的操作，助力每一位开发者在智能时代中脱颖而出。

未来，FEL 框架将支持更多的大模型接入，提供更强大的编排能力，帮助你构建更加智能、高效的应用。无论是企业级解决方案，还是个人项目，FEL 都将为你提供全方位的支持。🌟

---

官方网站：[地址](http://fitframework.io)

项目地址：[Github项目地址](https://github.com/ModelEngine-Group/fit-framework) [GitCode项目地址](https://gitcode.com/ModelEngine/fit-framework)

期待你的到来！

**技术人，用代码说话，用架构思考**

关注我们，探索更多「优雅解耦」的工程实践！🛠️

微信公众号：FitFramework
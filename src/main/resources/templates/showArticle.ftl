<#-- @ftlvariable name="article" type="com.example.models.Article" -->
<#-- @ftlvariable name="entities" type="kotlin.collections.List<com.example.models.Entity>"-->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>
            ${article.title}
        </h3>
        <p>
            ${article.body}
        </p>
        <hr>

        <h2>Associated Entities</h2>

        <#list entities as entity>
            <#if entity??>
                <h3>
                    <a href="/entities/${entity.id}">Entity: ${entity.name}</a>
                </h3>
                <p>Value: ${entity.value}</p>
                <p>SectionId: ${entity.sectionId}</p>
                <p>Order: ${entity.order}</p>
                <p>Description: ${entity.description}</p>
                <p>Id Article: ${entity.idArticle}</p>
            </#if>
        </#list>
        <hr>

        <p>
            <!-- Este enlace en la parte inferior de esta página debe abrir un formulario
             para editar o eliminar este artículo. -->
            <a href="/articles/${article.id}/edit">Edit article</a>
        </p>

    </div>
</@layout.header>
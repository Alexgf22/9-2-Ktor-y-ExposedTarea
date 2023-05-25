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
            <!--<li>ID: ${entity.id}, Value: ${entity.value}, Name: ${entity.name}</li>-->
            <#if entity??>
                <a href="/entities/${entity.id}">${entity.name}</a><br>
            </#if>
        </#list>



        <p>
            <!-- Este enlace en la parte inferior de esta página debe abrir un formulario
             para editar o eliminar este artículo. -->
            <a href="/articles/${article.id}/edit">Edit article</a>
        </p>

    </div>
</@layout.header>
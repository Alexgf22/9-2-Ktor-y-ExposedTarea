<#-- @ftlvariable name="article" type="com.example.models.Article" -->
<#-- @ftlvariable name="entities" type="com.example.models.Entity" -->
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


        <ul>
            <#list entities as entity>
                <li>ID: ${entity.id}, Value: ${entity.value}, Name: ${entity.name}</li>
            </#list>
        </ul>


        <p>
            <!-- Este enlace en la parte inferior de esta página debe abrir un formulario
             para editar o eliminar este artículo. -->
            <a href="/articles/${article.id}/edit">Edit article</a>
        </p>

    </div>
</@layout.header>
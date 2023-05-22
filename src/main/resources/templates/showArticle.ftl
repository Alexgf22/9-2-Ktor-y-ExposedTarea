<#-- @ftlvariable name="article" type="com.example.models.Article" -->
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
        <p>
            <!-- Este enlace en la parte inferior de esta página debe abrir un formulario
             para editar o eliminar este artículo. -->
            <a href="/articles/${article.id}/editArticle">Edit article</a>
        </p>
    </div>
</@layout.header>
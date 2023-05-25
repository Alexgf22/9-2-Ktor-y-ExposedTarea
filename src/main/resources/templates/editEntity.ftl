<#-- @ftlvariable name="entity" type="com.example.models.Entity" -->
<#-- @ftlvariable name="articles" type="kotlin.collections.List<com.example.models.Article>" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>Edit entity</h3>
        <form action="/entities/${entity.id}" method="post">
            <p>
                <label>
                    <input type="text" name="value" value="${entity.value}">
                </label>
            </p>

            <p>
                <label>
                    <input type="text" name="name" value="${entity.name}">
                </label>
            </p>

            <p>
                <label>
                    <textarea name="description">${entity.description}</textarea>
                </label>
            </p>

            <p>
                <label>
                    <input type="text" name="sectionId" value="${entity.sectionId}">
                </label>
            </p>

            <p>
                <label>
                    <input type="number" name="order" value="${entity.order}">
                </label>
            </p>

            <label for="selectArticle">Selecciona un artículo:</label>
            <!-- Aquí se añade un select para elegir un id de article -->
            <select id="selectArticle" name="articleId">
                <#list articles as article>
                    <option value="${article.id}">${article.id}</option>
                </#list>
            </select>

            <p>
                <input type="submit" name="_action" value="update">
            </p>
        </form>
    </div>
    <div>
        <form action="/entities/${entity.id}" method="post">
            <p>
                <input type="submit" name="_action" value="delete">
            </p>
        </form>
    </div>
</@layout.header>
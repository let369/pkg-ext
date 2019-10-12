# Package OpenUI extensions in your brXM project
This project demonstrates how to package OpenUI extensions as a separate module.  
This way your extensions will be deployed as a separate war (openui.war) in the same tomcat instance alongside cms and site.

The extensions are available under eg http://localhost:8080/openui/externalPicker  
The new module openui-extensions defines a single filter (org.example.filter.ExtensionsPathFilter) in its web.xml  
The purpose of this filter is to forward any trailing segments of the extension to the root (eg. http://localhost:8080/openui/externalPicker/dialog will be forwarded to http://localhost:8080/openui/externalPicker )  
The reason for this is so SPA routing works in the iframe. This approach will not affect single index.html extensions 

How to add a new extension
1. Add a new directory under openui-extensions/src/main/resources and paste all the required resources.
2. (Optional) In openui-extensions/pom.xml inside frontend-maven-plugin if required add new executions eg npm install & build.
3. In openui-extensions/pom.xml locate the maven-resources-plugin and add a new execution that will copy your resources to target.  
**IMPORTANT : the outputDirectory will define the URL your extensions is available under.**
4. In src/main/webapp/WEB-INF/web.xml locate the parameter extensions and append the name of your extension.
5. From the console application navigate to /hippo:configuration/hippo:frontend/cms/ui-extensions and register your new (page or field) extension.  
**IMPORTANT : the property frontend:url should be configured manually for each environment this get deployed to.**
6. (Optional) If it's a field extension follow the instructions how to add it to a document type.
/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.tutorial.routing;

import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouteParameterRegex;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.UrlParameters;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("routing/tutorial-routing-url-generation.asciidoc")
public class UrlTemplatesConfiguration {

    @Route(value = "item/:id([0-9]*)/edit")
    public static class ItemEdit extends Component {
    }

    @Route(value = ":path*")
    @RouteAlias(value = ":tab(api)/:path*")
    @RouteAlias(value = ":tab(overview|samples|links|reviews|discussions)")
    @RoutePrefix("component/:identifier")
    public static class ComponentView extends Component {
    }

    public class MenuView extends Div {
        final RouteConfiguration routeConfiguration = RouteConfiguration.forSessionScope();

        public MenuView() {
            addButtonApiLink();
            addItemEditLink();
        }

        private void addButtonApiLink() {
            String url = routeConfiguration.getUrl(
                    ComponentView.class,
                    new UrlParameters(
                            "identifier", "button",
                            "tab", "api",
                            "path", "com/vaadin/flow/button"));

            // The generated url is `component/button/api/com/vaadin/flow/button`
            Anchor link = new Anchor(url, "Button Api");
            add(link);
        }

        private void addItemEditLink() {
            String url = routeConfiguration.getUrl(
                    ComponentView.class,
                    new UrlParameters("id", "123"));

            // The generated url is `item/123/edit`
            Anchor link = new Anchor(url, "Button Api");
            add(link);
        }
    }

}


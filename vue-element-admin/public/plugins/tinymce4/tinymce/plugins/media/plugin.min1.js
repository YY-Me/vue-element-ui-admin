! function() {
	"use strict";
	var e = tinymce.util.Tools.resolve("tinymce.PluginManager"),
		o = tinymce.util.Tools.resolve("tinymce.Env"),
		v = tinymce.util.Tools.resolve("tinymce.util.Tools"),
		w = function(e) {
			return e.getParam("media_scripts")
		},
		b = function(e) {
			return e.getParam("audio_template_callback")
		},
		y = function(e) {
			return e.getParam("video_template_callback")
		},
		n = function(e) {
			return e.getParam("media_live_embeds", !0)
		},
		t = function(e) {
			return e.getParam("media_filter_html", !0)
		},
		s = function(e) {
			return e.getParam("media_url_resolver")
		},
		m = function(e) {
			return e.getParam("media_alt_source", !0)
		},
		d = function(e) {
			return e.getParam("media_poster", !0)
		},
		h = function(e) {
			return e.getParam("media_dimensions", !0)
		},
		p = tinymce.util.Tools.resolve("tinymce.html.SaxParser"),
		r = tinymce.util.Tools.resolve("tinymce.dom.DOMUtils"),
		x = function(e, t) {
			if (e)
				for (var r = 0; r < e.length; r++)
					if (-1 !== t.indexOf(e[r].filter)) return e[r]
		},
		i = function(t) {
			return function(e) {
				return e ? e.style[t].replace(/px$/, "") : ""
			}
		},
		a = function(i) {
			return function(e, t) {
				var r;
				e && (e.style[i] = /^[0-9.]+$/.test(r = t) ? r + "px" : r)
			}
		},
		f = {
			getMaxWidth: i("maxWidth"),
			getMaxHeight: i("maxHeight"),
			setMaxWidth: a("maxWidth"),
			setMaxHeight: a("maxHeight")
		},
		u = r.DOM,
		l = function(e) {
			return u.getAttrib(e, "data-ephox-embed-iri")
		},
		j = function(e, t) {
			return c = t, s = u.createFragment(c), "" !== l(s.firstChild) ? (o = t, n = u.createFragment(o).firstChild, {
				type: "ephox-embed-iri",
				source1: l(n),
				source2: "",
				poster: "",
				width: f.getMaxWidth(n),
				height: f.getMaxHeight(n)
			}) : (i = e, r = t, p({
				validate: (a = {}, !1),
				allow_conditional_comments: !0,
				special: "script,noscript",
				start: function(e, t) {
					if (a.source1 || "param" !== e || (a.source1 = t.map.movie), "iframe" !== e && "object" !== e && "embed" !==
						e && "video" !== e && "audio" !== e || (a.type || (a.type = e), a = v.extend(t.map, a)), "script" === e) {
						var r = x(i, t.map.src);
						if (!r) return;
						a = {
							type: "script",
							source1: t.map.src,
							width: r.width,
							height: r.height
						}
					}
					"source" === e && (a.source1 ? a.source2 || (a.source2 = t.map.src) : a.source1 = t.map.src), "img" !== e || a
						.poster || (a.poster = t.map.src)
				}
			}).parse(r), a.source1 = a.source1 || a.src || a.data, a.source2 = a.source2 || "", a.poster = a.poster || "", a);
			var i, r, a, o, n, c, s
		},
		g = tinymce.util.Tools.resolve("tinymce.util.Promise"),
		M = function(e) {
			var t = {
				mp3: "audio/mpeg",
				wav: "audio/wav",
				mp4: "video/mp4",
				webm: "video/webm",
				ogg: "video/ogg",
				swf: "application/x-shockwave-flash"
			} [e.toLowerCase().split(".").pop()];
			return t || ""
		},
		_ = tinymce.util.Tools.resolve("tinymce.html.Writer"),
		C = tinymce.util.Tools.resolve("tinymce.html.Schema"),
		S = r.DOM,
		F = function(e, t) {
			var r, i, a, o;
			for (r in t)
				if (a = "" + t[r], e.map[r])
					for (i = e.length; i--;)(o = e[i]).name === r && (a ? (e.map[r] = a, o.value = a) : (delete e.map[r], e.splice(i,
						1)));
				else a && (e.push({
					name: r,
					value: a
				}), e.map[r] = a)
		},
		k = function(e, t) {
			var r, i, a = S.createFragment(e).firstChild;
			return f.setMaxWidth(a, t.width), f.setMaxHeight(a, t.height), r = a.outerHTML, i = _(), p(i).parse(r), i.getContent()
		},
		A = function(e, t, r) {
			return u = e, l = S.createFragment(u), "" !== S.getAttrib(l.firstChild, "data-ephox-embed-iri") ? k(e, t) : (i = e,
				a = t, o = r, c = _(), p({
					validate: !1,
					allow_conditional_comments: !(s = 0),
					special: "script,noscript",
					comment: function(e) {
						c.comment(e)
					},
					cdata: function(e) {
						c.cdata(e)
					},
					text: function(e, t) {
						c.text(e, t)
					},
					start: function(e, t, r) {
						switch (e) {
							case "video":
							case "object":
							case "embed":
							case "img":
							case "iframe":
								a.height !== undefined && a.width !== undefined && F(t, {
									width: a.width,
									height: a.height
								})
						}
						if (o) switch (e) {
							case "video":
								F(t, {
									poster: a.poster,
									src: ""
								}), a.source2 && F(t, {
									src: ""
								});
								break;
							case "iframe":
								F(t, {
									src: a.source1
								});
								break;
							case "source":
								if (++s <= 2 && (F(t, {
										src: a["source" + s],
										type: a["source" + s + "mime"]
									}), !a["source" + s])) return;
								break;
							case "img":
								if (!a.poster) return;
								n = !0
						}
						c.start(e, t, r)
					},
					end: function(e) {
						if ("video" === e && o)
							for (var t = 1; t <= 2; t++)
								if (a["source" + t]) {
									var r = [];
									r.map = {}, s < t && (F(r, {
										src: a["source" + t],
										type: a["source" + t + "mime"]
									}), c.start("source", r, !0))
								} if (a.poster && "object" === e && o && !n) {
							var i = [];
							i.map = {}, F(i, {
								src: a.poster,
								width: a.width,
								height: a.height
							}), c.start("img", i, !0)
						}
						c.end(e)
					}
				}, C({})).parse(i), c.getContent());
			var i, a, o, n, c, s, u, l
		},
		N = [{
			regex: /youtu\.be\/([\w\-_\?&=.]+)/i,
			type: "iframe",
			w: 560,
			h: 314,
			url: "//www.youtube.com/embed/$1",
			allowFullscreen: !0
		}, {
			regex: /youtube\.com(.+)v=([^&]+)(&([a-z0-9&=\-_]+))?/i,
			type: "iframe",
			w: 560,
			h: 314,
			url: "//www.youtube.com/embed/$2?$4",
			allowFullscreen: !0
		}, {
			regex: /youtube.com\/embed\/([a-z0-9\?&=\-_]+)/i,
			type: "iframe",
			w: 560,
			h: 314,
			url: "//www.youtube.com/embed/$1",
			allowFullscreen: !0
		}, {
			regex: /vimeo\.com\/([0-9]+)/,
			type: "iframe",
			w: 425,
			h: 350,
			url: "//player.vimeo.com/video/$1?title=0&byline=0&portrait=0&color=8dc7dc",
			allowFullscreen: !0
		}, {
			regex: /vimeo\.com\/(.*)\/([0-9]+)/,
			type: "iframe",
			w: 425,
			h: 350,
			url: "//player.vimeo.com/video/$2?title=0&amp;byline=0",
			allowFullscreen: !0
		}, {
			regex: /maps\.google\.([a-z]{2,3})\/maps\/(.+)msid=(.+)/,
			type: "iframe",
			w: 425,
			h: 350,
			url: '//maps.google.com/maps/ms?msid=$2&output=embed"',
			allowFullscreen: !1
		}, {
			regex: /dailymotion\.com\/video\/([^_]+)/,
			type: "iframe",
			w: 480,
			h: 270,
			url: "//www.dailymotion.com/embed/video/$1",
			allowFullscreen: !0
		}, {
			regex: /dai\.ly\/([^_]+)/,
			type: "iframe",
			w: 480,
			h: 270,
			url: "//www.dailymotion.com/embed/video/$1",
			allowFullscreen: !0
		}],
		c = function(r, e) {
			var i = v.extend({}, e);
			if (!i.source1 && (v.extend(i, j(w(r), i.embed)), !i.source1)) return "";
			i.source2 || (i.source2 = ""), i.poster || (i.poster = ""), i.source1 = r.convertURL(i.source1, "source"), i.source2 =
				r.convertURL(i.source2, "source"), i.source1mime = M(i.source1), i.source2mime = M(i.source2), i.poster = r.convertURL(
					i.poster, "poster");
			var t, a, o = (t = i.source1, 0 < (a = N.filter(function(e) {
				return e.regex.test(t)
			})).length ? v.extend({}, a[0], {
				url: function(e, t) {
					for (var r = e.regex.exec(t), i = e.url, a = function(e) {
							i = i.replace("$" + e, function() {
								return r[e] ? r[e] : ""
							})
						}, o = 0; o < r.length; o++) a(o);
					return i.replace(/\?$/, "")
				}(a[0], t)
			}) : null);
			if (o && (i.source1 = o.url, i.type = o.type, i.allowFullscreen = o.allowFullscreen, i.width = i.width || o.w, i.height =
					i.height || o.h), i.embed) return A(i.embed, i, !0);
			var n = x(w(r), i.source1);
			n && (i.type = "script", i.width = n.width, i.height = n.height);
			var c, s, u, l, m, d, h, p, f = b(r),
				g = y(r);
			return i.width = i.width || 300, i.height = i.height || 150, v.each(i, function(e, t) {
					i[t] = r.dom.encode(e)
				}), "iframe" === i.type ? (p = (h = i).allowFullscreen ? ' allowFullscreen="1"' : "", '<iframe src="' + h.source1 +
					'" width="' + h.width + '" height="' + h.height + '"' + p + "></iframe>") : "application/x-shockwave-flash" === i.source1mime ?
				(d = '<object data="' + (m = i).source1 + '" width="' + m.width + '" height="' + m.height +
					'" type="application/x-shockwave-flash">', m.poster && (d += '<img src="' + m.poster + '" width="' + m.width +
						'" height="' + m.height + '" />'), d += "</object>") : -1 !== i.source1mime.indexOf("audio") ? (u = i, (l = f) ?
					l(u) : '<audio controls="controls" src="' + u.source1 + '">' + (u.source2 ? '\n<source src="' + u.source2 + '"' +
						(u.source2mime ? ' type="' + u.source2mime + '"' : "") + " />\n" : "") + "</audio>") : "script" === i.type ?
				'<script src="' + i.source1 + '"><\/script>' : (c = i, (s = g) ? s(c) : '<video width="' + c.width + '" height="' +
					c.height + '"' + (c.poster ? ' poster="' + c.poster + '"' : "") + ' controls="controls">\n<source src="' + c.source1 +
					'"' + (c.source1mime ? ' type="' + c.source1mime + '"' : "") + " />\n" + (c.source2 ? '<source src="' + c.source2 +
						'"' + (c.source2mime ? ' type="' + c.source2mime + '"' : "") + " />\n" : "") + "</video>")
		},
		O = {},
		P = function(t) {
			return function(e) {
				return c(t, e)
			}
		},
		T = function(e, t) {
			var r, i, a, o, n, c = s(e);
			return c ? (a = t, o = P(e), n = c, new g(function(t, e) {
				var r = function(e) {
					return e.html && (O[a.source1] = e), t({
						url: a.source1,
						html: e.html ? e.html : o(a)
					})
				};
				O[a.source1] ? r(O[a.source1]) : n({
					url: a.source1
				}, r, e)
			})) : (r = t, i = P(e), new g(function(e) {
				e({
					html: i(r),
					url: r.source1
				})
			}))
		},
		$ = function(e) {
			return O.hasOwnProperty(e)
		},
		z = function(e, t) {
			e.state.set("oldVal", e.value()), t.state.set("oldVal", t.value())
		},
		L = function(e, t) {
			var r = e.find("#width")[0],
				i = e.find("#height")[0],
				a = e.find("#constrain")[0];
			r && i && a && t(r, i, a.checked())
		},
		H = function(e, t, r) {
			var i = e.state.get("oldVal"),
				a = t.state.get("oldVal"),
				o = e.value(),
				n = t.value();
			r && i && a && o && n && (o !== i ? (n = Math.round(o / i * n), isNaN(n) || t.value(n)) : (o = Math.round(n / a * o),
				isNaN(o) || e.value(o))), z(e, t)
		},
		W = function(e) {
			L(e, H)
		},
		J = function(e) {
			var t = function() {
				e(function(e) {
					W(e)
				})
			};
			return {
				type: "container",
				label: "Dimensions",
				layout: "flex",
				align: "center",
				spacing: 5,
				items: [{
					name: "width",
					type: "textbox",
					maxLength: 5,
					size: 5,
					onchange: t,
					ariaLabel: "Width"
				}, {
					type: "label",
					text: "x"
				}, {
					name: "height",
					type: "textbox",
					maxLength: 5,
					size: 5,
					onchange: t,
					ariaLabel: "Height"
				}, {
					name: "constrain",
					type: "checkbox",
					checked: !0,
					text: "Constrain proportions"
				}]
			}
		},
		R = function(e) {
			L(e, z)
		},
		D = W,
		E = o.ie && o.ie <= 8 ? "onChange" : "onInput",
		I = function(r) {
			return function(e) {
				var t = e && e.msg ? "Media embed handler error: " + e.msg : "Media embed handler threw unknown error.";
				r.notificationManager.open({
					type: "error",
					text: t
				})
			}
		},
		U = function(a, o) {
			return function(e) {
				var t = e.html,
					r = a.find("#embed")[0],
					i = v.extend(j(w(o), t), {
						source1: e.url
					});
				a.fromJSON(i), r && (r.value(t), D(a))
			}
		},
		V = function(e, t) {
			var r = e.dom.select("img[data-mce-object]");
			e.insertContent(t),
				function(e, t) {
					var r, i, a = e.dom.select("img[data-mce-object]");
					for (r = 0; r < t.length; r++)
						for (i = a.length - 1; 0 <= i; i--) t[r] === a[i] && a.splice(i, 1);
					e.selection.select(a[0])
				}(e, r), e.nodeChanged()
		},
		B = function(i) {
			var a, t, e, r, o, n = [{
					name: "source1",
					type: "filepicker",
					filetype: "media",
					size: 40,
					autofocus: !0,
					label: "Source",
					onpaste: function() {
						setTimeout(function() {
							T(i, a.toJSON()).then(U(a, i))["catch"](I(i))
						}, 1)
					},
					onchange: function(e) {
						var r, t;
						T(i, a.toJSON()).then(U(a, i))["catch"](I(i)), r = a, t = e.meta, v.each(t, function(e, t) {
							r.find("#" + t).value(e)
						})
					},
					onbeforecall: function(e) {
						e.meta = a.toJSON()
					}
				}],
				c = [];
			if (m(i) && c.push({
					name: "source2",
					type: "filepicker",
					filetype: "media",
					size: 40,
					label: "Alternative source"
				}), d(i) && c.push({
					name: "poster",
					type: "filepicker",
					filetype: "image",
					size: 40,
					label: "Poster"
				}), h(i)) {
				var s = J(function(e) {
					e(a), t = a.toJSON(), a.find("#embed").value(A(t.embed, t))
				});
				n.push(s)
			}
			r = (e = i).selection.getNode(), o = r.getAttribute("data-ephox-embed-iri"), t = o ? {
				source1: o,
				"data-ephox-embed-iri": o,
				width: f.getMaxWidth(r),
				height: f.getMaxHeight(r)
			} : r.getAttribute("data-mce-object") ? j(w(e), e.serializer.serialize(r, {
				selection: !0
			})) : {};
			var u = {
				id: "mcemediasource",
				type: "textbox",
				flex: 1,
				name: "embed",
				value: function(e) {
					var t = e.selection.getNode();
					if (t.getAttribute("data-mce-object") || t.getAttribute("data-ephox-embed-iri")) return e.selection.getContent()
				}(i),
				multiline: !0,
				rows: 5,
				label: "Source"
			};
			u[E] = function() {
				t = v.extend({}, j(w(i), this.value())), this.parent().parent().fromJSON(t)
			};
			var l = [{
				title: "General",
				type: "form",
				items: n
			}, {
				title: "Embed",
				type: "container",
				layout: "flex",
				direction: "column",
				align: "stretch",
				padding: 10,
				spacing: 10,
				items: [{
					type: "label",
					text: "Paste your embed code below:",
					forId: "mcemediasource"
				}, u]
			}];
			0 < c.length && l.push({
				title: "Advanced",
				type: "form",
				items: c
			}), a = i.windowManager.open({
				title: "Insert/edit media",
				data: t,
				bodyType: "tabpanel",
				body: l,
				onSubmit: function() {
					var t, e;
					D(a), t = i, (e = a.toJSON()).embed = A(e.embed, e), e.embed && $(e.source1) ? V(t, e.embed) : T(t, e).then(
						function(e) {
							V(t, e.html)
						})["catch"](I(t))
				}
			}), R(a)
		},
		G = function(e) {
			return {
				showDialog: function() {
					B(e)
				}
			}
		},
		q = function(e) {
			e.addCommand("mceMedia", function() {
				B(e)
			})
		},
		K = tinymce.util.Tools.resolve("tinymce.html.Node"),
		Q = function(a, e) {
			if (!1 === t(a)) return e;
			var o, n = _();
			return p({
				validate: !1,
				allow_conditional_comments: !1,
				special: "script,noscript",
				comment: function(e) {
					n.comment(e)
				},
				cdata: function(e) {
					n.cdata(e)
				},
				text: function(e, t) {
					n.text(e, t)
				},
				start: function(e, t, r) {
					if (o = !0, "script" !== e && "noscript" !== e) {
						for (var i = 0; i < t.length; i++) {
							if (0 === t[i].name.indexOf("on")) return;
							"style" === t[i].name && (t[i].value = a.dom.serializeStyle(a.dom.parseStyle(t[i].value), e))
						}
						n.start(e, t, r), o = !1
					}
				},
				end: function(e) {
					o || n.end(e)
				}
			}, C({})).parse(e), n.getContent()
		},
		X = function(e, t) {
			var r, i = t.name;
			return (r = new K("img", 1)).shortEnded = !0, Z(e, t, r), r.attr({
				width: t.attr("width") || "300",
				height: t.attr("height") || ("audio" === i ? "30" : "150"),
				style: t.attr("style"),
				src: o.transparentSrc,
				"data-mce-object": i,
				"class": "mce-object mce-object-" + i
			}), r
		},
		Y = function(e, t) {
			var r, i, a, o = t.name;
			return (r = new K("span", 1)).attr({
				contentEditable: "false",
				style: t.attr("style"),
				"data-mce-object": o,
				"class": "mce-preview-object mce-object-" + o
			}), Z(e, t, r), (i = new K(o, 1)).attr({
				src: t.attr("src"),
				allowfullscreen: t.attr("allowfullscreen"),
				style: t.attr("style"),
				"class": t.attr("class"),
				width: t.attr("width"),
				height: t.attr("height"),
				frameborder: "0"
			}), (a = new K("span", 1)).attr("class", "mce-shim"), r.append(i), r.append(a), r
		},
		Z = function(e, t, r) {
			var i, a, o, n, c;
			for (n = (o = t.attributes).length; n--;) i = o[n].name, a = o[n].value, "width" !== i && "height" !== i && "style" !==
				i && ("data" !== i && "src" !== i || (a = e.convertURL(a, i)), r.attr("data-mce-p-" + i, a));
			(c = t.firstChild && t.firstChild.value) && (r.attr("data-mce-html", escape(Q(e, c))), r.firstChild = null)
		},
		ee = function(e) {
			for (; e = e.parent;)
				if (e.attr("data-ephox-embed-iri")) return !0;
			return !1
		},
		te = function(a) {
			return function(e) {
				for (var t, r, i = e.length; i--;)(t = e[i]).parent && (t.parent.attr("data-mce-object") || ("script" !== t.name ||
					(r = x(w(a), t.attr("src")))) && (r && (r.width && t.attr("width", r.width.toString()), r.height && t.attr(
						"height", r.height.toString())), "iframe" === t.name && n(a) && o.ceFalse ? ee(t) || t.replace(Y(a, t)) : ee(t) ||
					t.replace(X(a, t))))
			}
		},
		re = function(d) {
			d.on("preInit", function() {
				var t = d.schema.getSpecialElements();
				v.each("video audio iframe object".split(" "), function(e) {
					t[e] = new RegExp("</" + e + "[^>]*>", "gi")
				});
				var r = d.schema.getBoolAttrs();
				v.each("webkitallowfullscreen mozallowfullscreen allowfullscreen".split(" "), function(e) {
					r[e] = {}
				}), d.parser.addNodeFilter("iframe,video,audio,object,embed,script", te(d)), d.serializer.addAttributeFilter(
					"data-mce-object",
					function(e, t) {
						for (var r, i, a, o, n, c, s, u, l = e.length; l--;)
							if ((r = e[l]).parent) {
								for (s = r.attr(t), i = new K(s, 1), "audio" !== s && "script" !== s && ((u = r.attr("class")) && -1 !== u.indexOf(
										"mce-preview-object") ? i.attr({
										width: r.firstChild.attr("width"),
										height: r.firstChild.attr("height")
									}) : i.attr({
										width: r.attr("width"),
										height: r.attr("height")
									})), i.attr({
										style: r.attr("style")
									}), a = (o = r.attributes).length; a--;) {
									var m = o[a].name;
									0 === m.indexOf("data-mce-p-") && i.attr(m.substr(11), o[a].value)
								}
								"script" === s && i.attr("type", "text/javascript"), (n = r.attr("data-mce-html")) && ((c = new K("#text", 3))
									.raw = !0, c.value = Q(d, unescape(n)), i.append(c)), r.replace(i)
							}
					})
			}), d.on("setContent", function() {
				d.$("span.mce-preview-object").each(function(e, t) {
					var r = d.$(t);
					0 === r.find("span.mce-shim", t).length && r.append('<span class="mce-shim"></span>')
				})
			})
		},
		ie = function(e) {
			e.on("ResolveName", function(e) {
				var t;
				1 === e.target.nodeType && (t = e.target.getAttribute("data-mce-object")) && (e.name = t)
			})
		},
		ae = function(t) {
			t.on("click keyup", function() {
				var e = t.selection.getNode();
				e && t.dom.hasClass(e, "mce-preview-object") && t.dom.getAttrib(e, "data-mce-selected") && e.setAttribute(
					"data-mce-selected", "2")
			}), t.on("ObjectSelected", function(e) {
				var t = e.target.getAttribute("data-mce-object");
				"audio" !== t && "script" !== t || e.preventDefault()
			}), t.on("objectResized", function(e) {
				var t, r = e.target;
				r.getAttribute("data-mce-object") && (t = r.getAttribute("data-mce-html")) && (t = unescape(t), r.setAttribute(
					"data-mce-html", escape(A(t, {
						width: e.width,
						height: e.height
					}))))
			})
		},
		oe = function(e) {
			e.addButton("media", {
				tooltip: "Insert/edit media",
				cmd: "mceMedia",
				stateSelector: ["img[data-mce-object]", "span[data-mce-object]", "div[data-ephox-embed-iri]"]
			}), e.addMenuItem("media", {
				icon: "media",
				text: "Media",
				cmd: "mceMedia",
				context: "insert",
				prependToContext: !0
			})
		};
	e.add("media", function(e) {
		return q(e), oe(e), ie(e), re(e), ae(e), G(e)
	})
}();

/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 * 
 *    (C) 2006-2008, Open Source Geospatial Foundation (OSGeo)
 *    
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.filter.text.cql_2.parsers;

/**
 * Redefines SimpleNode to allow access to additional 
 * information to the {@link CQLCompiler}
 * 
 * Note: set the option BUILD_NODE_FILES=false to avoid that javacc generates
 * a new SimpleNode.          
 *
 * @author Mauricio Pazos (Axios Engineering)
 * @since
 *
 *

 */
public class SimpleNode implements  Node {
  protected Node parent;
  protected Node[] children;
  protected int id;
  protected CQL2Parser parser;
  protected Token token;

  public SimpleNode(int i) {
    id = i;
  }

  public SimpleNode(CQL2Parser p, int i) {
    this(i);
    parser = p;
  }

  /* (non-Javadoc)
 * @see org.geotools.filter.text.cql2.CQLNode#jjtOpen()
 */
/* (non-Javadoc)
 * @see org.geotools.filter.text.generated.parsers.Node#jjtOpen()
 */
public void jjtOpen() {
  }

  /* (non-Javadoc)
 * @see org.geotools.filter.text.cql2.CQLNode#jjtClose()
 */
/* (non-Javadoc)
 * @see org.geotools.filter.text.generated.parsers.Node#jjtClose()
 */
public void jjtClose() {
  }
  
  /* (non-Javadoc)
 * @see org.geotools.filter.text.cql2.CQLNode#jjtSetParent(org.geotools.filter.text.cql2.Node)
 */
public void jjtSetParent(Node n) { parent = n; }
  /* (non-Javadoc)
 * @see org.geotools.filter.text.cql2.CQLNode#jjtGetParent()
 */
public Node jjtGetParent() { return parent; }

  /* (non-Javadoc)
 * @see org.geotools.filter.text.cql2.CQLNode#jjtAddChild(org.geotools.filter.text.cql2.Node, int)
 */
public void jjtAddChild(Node n, int i) {
    if (children == null) {
      children = new Node[i + 1];
    } else if (i >= children.length) {
      Node c[] = new Node[i + 1];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
    }
    children[i] = n;
  }

  /* (non-Javadoc)
 * @see org.geotools.filter.text.cql2.CQLNode#jjtGetChild(int)
 */
public Node jjtGetChild(int i) {
    return children[i];
  }

  /* (non-Javadoc)
 * @see org.geotools.filter.text.cql2.CQLNode#jjtGetNumChildren()
 */
/* (non-Javadoc)
 * @see org.geotools.filter.text.generated.parsers.Node#jjtGetNumChildren()
 */
public int jjtGetNumChildren() {
    return (children == null) ? 0 : children.length;
  }
  
 
  /* You can override these two methods in subclasses of SimpleNode to
     customize the way the node appears when the tree is dumped.  If
     your output uses more than one line you should override
     toString(String), otherwise overriding toString() is probably all
     you need to do. */

  /* (non-Javadoc)
 * @see org.geotools.filter.text.cql2.CQLNode#toString()
 */
/* (non-Javadoc)
 * @see org.geotools.filter.text.generated.parsers.Node#toString()
 */
public String toString() { return CQL2ParserTreeConstants.jjtNodeName[id]; }
  /* (non-Javadoc)
 * @see org.geotools.filter.text.cql2.CQLNode#toString(java.lang.String)
 */
/* (non-Javadoc)
 * @see org.geotools.filter.text.generated.parsers.Node#toString(java.lang.String)
 */
public String toString(String prefix) { return prefix + toString(); }

  /* Override this method if you want to customize how the node dumps
     out its children. */

  /* (non-Javadoc)
 * @see org.geotools.filter.text.cql2.CQLNode#dump(java.lang.String)
 */
/* (non-Javadoc)
 * @see org.geotools.filter.text.generated.parsers.Node#dump(java.lang.String)
 */
public void dump(String prefix) {
    // System.out.println(toString(prefix));
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
	Node n = (Node)children[i];
	if (n != null) {
	  n.dump(prefix + " ");
	}
      }
    }
  }
  
  /* (non-Javadoc)
 * @see org.geotools.filter.text.cql2.CQLNode#getType()
 */
/* (non-Javadoc)
 * @see org.geotools.filter.text.generated.parsers.Node#getType()
 */
public int getType() {
      return id;
  }
  
  /* (non-Javadoc)
 * @see org.geotools.filter.text.cql2.CQLNode#dispose()
 */
/* (non-Javadoc)
 * @see org.geotools.filter.text.generated.parsers.Node#dispose()
 */
public void dispose() {
      parent = null;
      children = null;
      parser = null;
  }
  
  /* (non-Javadoc)
 * @see org.geotools.filter.text.cql2.CQLNode#getToken()
 */
/* (non-Javadoc)
 * @see org.geotools.filter.text.generated.parsers.Node#getToken()
 */
public Token getToken() {
      return token;
  }
  
}


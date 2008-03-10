/**
 * Copyright (C) 2006-2008 eIrOcA (eNrIcO Croce & sImOnA Burzio)
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details. You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the:
 *   Free Software Foundation, Inc.,
 *   51 Franklin St, Fifth Floor,
 *   Boston, MA 02110-1301
 *   USA
 */
package com.fbergeron.organigram.io;

public class TAG {

  private final String tag;

  public TAG(final String tag) {
    this.tag = tag.toLowerCase();
  }

  public void open(final StringBuffer sb, boolean open) {
    sb.append('<');
    sb.append(tag);
    if (!open) {
      sb.append('>');
    }
  }

  public void openClose(final StringBuffer sb) {
    sb.append('>');
  }

  public void writeCData(final StringBuffer sb, final String data) {
    if (data != null) {
      TAG.encode(sb, data);
    }
  }

  public static void encode(final StringBuffer sb, final String str) {
    if (str.startsWith("Support the implementation")) {
      sb.append('x');
    }

    for (int i = 0; i < str.length(); i++) {
      final char c = str.charAt(i);
      if (c < 32) {
        sb.append(' ');
      }
      else {
        switch (c) {
        case '&':
          sb.append("&amp;");
          break;
        default:
          sb.append(c);
          break;
        }
      }
    }
  }

  public void writeAttribute(final StringBuffer sb, final String name, final String value, final boolean last) {
    sb.append(' ');
    sb.append(name);
    sb.append("=\"");
    TAG.encode(sb, value);
    sb.append("\"");
    if (last) {
      sb.append('>');
    }
  }

  public void close(final StringBuffer sb, final boolean compact) {
    if (compact) {
      sb.append("/>");
    }
    else {
      sb.append("</");
      sb.append(tag);
      sb.append('>');
    }
  }

}

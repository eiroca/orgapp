/**
 * Copyright (C) 2006-2010 eIrOcA (eNrIcO Croce & sImOnA Burzio)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/
 * 
 */
package com.fbergeron.organigram.io.sitemap;

import com.fbergeron.organigram.io.TagProcessor;
import com.fbergeron.organigram.io.XMLHandler;

/**
 * The Class XMLOrganigramReader.
 */
public class SiteMapReader extends XMLHandler {

  /** Only to initialize SiteMapUtils */
  public final TagProcessor tag = SiteMapUtils.URLSET;

}

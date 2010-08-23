/** LGPL > 3.0
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
 */
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.util.OrgCharFixUp;
import com.fbergeron.organigram.util.OrgUtils;
import com.fbergeron.organigram.util.Utils;
import com.fbergeron.organigram.view.OrganigramImage;

/**
 * The Class Convert.
 */
public class Org2img {

  /**
   * The main method.
   * 
   * @param args the arguments
   */
  public static void main(final String[] args) {
    if (args.length != 2) {
      System.out.println("Usage: ");
      System.out.println("java -jar Org2img.jar organigram_file output_file");
      System.out.println("output_file must end with a ImageIO supported format");
      System.exit(1);
    }
    final String inFile = args[0];
    Organigram org = null;
    final URL xmlSource = Utils.findResource(inFile);
    if (xmlSource != null) {
      org = OrgUtils.readOrganigram(xmlSource);
    }
    if (org != null) {
      new OrgCharFixUp().execute(org, true, null);
      final OrganigramImage img = new OrganigramImage(org);
      final File output = new File(args[1]);
      try {
        ImageIO.write(img.getImage(), Utils.getExtension(output), output);
      }
      catch (final IOException e) {
        System.err.println("Output error ->" + e.toString());
      }
    }
    else {
      System.err.println("Invalid organigram ->" + args[0]);
    }
  }

}

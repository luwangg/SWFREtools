package tv.porst.swfretools.parser.tags;

import tv.porst.splib.io.BinaryParser;
import tv.porst.swfretools.parser.structures.CxForm;
import tv.porst.swfretools.parser.structures.CxFormParser;
import tv.porst.swfretools.parser.structures.Matrix;
import tv.porst.swfretools.parser.structures.MatrixParser;
import tv.porst.swfretools.parser.structures.RecordHeader;

public class PlaceObjectParser {

	public static Tag parse(final RecordHeader header, final BinaryParser parser) {
		final int characterId = parser.readUInt16();
		final int depth = parser.readUInt16();
		final Matrix matrix = MatrixParser.parse(parser);
		final CxForm colorTransform = CxFormParser.parse(parser);

		return new PlaceObjectTag(header, characterId, depth, matrix, colorTransform);
	}

}
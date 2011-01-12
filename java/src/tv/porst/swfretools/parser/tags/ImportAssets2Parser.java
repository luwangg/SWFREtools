package tv.porst.swfretools.parser.tags;

import static tv.porst.swfretools.parser.SWFParserHelpers.parseString;
import static tv.porst.swfretools.parser.SWFParserHelpers.parseUINT16;
import static tv.porst.swfretools.parser.SWFParserHelpers.parseUINT8;

import java.util.ArrayList;
import java.util.List;

import tv.porst.splib.io.PString;
import tv.porst.splib.io.UINT16;
import tv.porst.splib.io.UINT8;
import tv.porst.swfretools.parser.SWFBinaryParser;
import tv.porst.swfretools.parser.SWFParserException;
import tv.porst.swfretools.parser.structures.AssetParser;
import tv.porst.swfretools.parser.structures.AssetTag;
import tv.porst.swfretools.parser.structures.RecordHeader;

/**
 * Class for parsing ImportAssets2 tags.
 * 
 * @author sp
 */
public final class ImportAssets2Parser {

	/**
	 * Parses an ImportAssets2 tag.
	 * 
	 * @param parser Provides the input data.
	 * @param header Previously parsed header of the tag.
	 * 
	 * @return Returns the parsed tag.
	 * 
	 * @throws SWFParserException Thrown if parsing the tag failed.
	 */
	public static ImportAssets2Tag parse(final RecordHeader header, final SWFBinaryParser parser) throws SWFParserException {

		final PString url = parseString(parser, 0x00006, "ImportAssets2::URL");
		final UINT8 reserved = parseUINT8(parser, 0x00006, "ImportAssets2::Reserved");
		final UINT8 reserved2 = parseUINT8(parser, 0x00006, "ImportAssets2::Reserved2");
		final UINT16 count = parseUINT16(parser, 0x00006, "ImportAssets2::Count");

		final List<AssetTag> tags = new ArrayList<AssetTag>();

		for (int i=0;i<count.value();i++) {
			tags.add(AssetParser.parse(parser, String.format("ImportAssets2::Tags[%d]", i)));
		}

		return new ImportAssets2Tag(header, url, reserved, reserved2, count, tags);
	}
}
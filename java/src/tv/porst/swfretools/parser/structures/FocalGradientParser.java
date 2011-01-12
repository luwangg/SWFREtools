package tv.porst.swfretools.parser.structures;

import java.util.ArrayList;
import java.util.List;

import tv.porst.swfretools.parser.SWFBinaryParser;
import tv.porst.swfretools.parser.SWFParserException;

public class FocalGradientParser {

	public static FocalGradient parse(final SWFBinaryParser parser) throws SWFParserException {
		final int spreadMode = parser.readBits(2);
		final int interpolationMode = parser.readBits(2);
		final int numGradients = parser.readBits(4);

		final List<GradRecord> gradientRecords = new ArrayList<GradRecord>();

		for (int i=0;i<numGradients;i++) {
			gradientRecords.add(GradRecordParser.parse(parser, String.format("GradientRecord[%d]", i)));
		}

		final Fixed8 focalPoint = Fixed8Parser.parse(parser);

		return new FocalGradient(spreadMode, interpolationMode, numGradients, gradientRecords, focalPoint);
	}

}

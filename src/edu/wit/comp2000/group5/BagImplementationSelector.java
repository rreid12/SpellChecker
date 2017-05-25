package edu.wit.comp2000.group5;

/**
 * @author David M Rosenberg
 *
 */
public enum BagImplementationSelector {
	// Type Display Name
	LINKED("Linked"), FIXED_ARRAY("Fixed Array"), RESIZABLE_ARRAY("Resizable Array");

	public final String displayName;

	/**
	 * 
	 * @param itemWeightDisplayName
	 * @param itemWeightValue
	 */
	private BagImplementationSelector(String implementationDisplayName) {
		displayName = implementationDisplayName;
	} // end constructor

	/**
	 * 
	 * @param implementationDescription
	 * @return
	 */
	public static BagImplementationSelector interpretDescription(String implementationDescription) {
		// TODO: really implement this
		BagImplementationSelector correspondingImplementation;

		switch (implementationDescription.toLowerCase().charAt(0)) {
		case 'l':
			correspondingImplementation = LINKED;
			break;

		case 'f':
		case 'a':
			correspondingImplementation = FIXED_ARRAY;
			break;

		case 'r':
			correspondingImplementation = RESIZABLE_ARRAY;
			break;

		default:
			correspondingImplementation = LINKED;
			break;
		} // end switch()

		return correspondingImplementation;
	} // end method interpretDescription()

	/**
	 * 
	 */
	@Override
	public String toString() {
		return displayName;
	} // end method toString()

} // end enum BagImplementationSelector

package dk.sdu.se_f22.sortingmodule.range.rangepublic;

import dk.sdu.se_f22.sharedlibrary.models.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class LongFilter extends RangeFilterClass{
    private final long DB_MIN;
    private final long DB_MAX;
    private long userMin;
    private long userMax;

    public LongFilter(int ID, String NAME, String DESCRIPTION, String PRODUCT_ATTRIBUTE, long dbMin, long dbMax) {
        super(ID, NAME, DESCRIPTION, PRODUCT_ATTRIBUTE,
                List.of(new String[]{"ean"}));
        // The list with a single element seems silly for now,
        // but makes it so much easier to expand and add more attributes of the type long

        DB_MIN = dbMin;
        DB_MAX = dbMax;
    }

    public LongFilter(String NAME, String DESCRIPTION, String PRODUCT_ATTRIBUTE, long dbMin, long dbMax) {
        super(NAME, DESCRIPTION, PRODUCT_ATTRIBUTE,
                List.of(new String[]{"ean"}));
        DB_MIN = dbMin;
        DB_MAX = dbMax;
    }

    @Override
    public FilterTypes getType() {
        return FilterTypes.LONG;
    }


    @Override
    public boolean equals(Object other) {
        if(! super.equals(other)) {
            return false;
        }

        if(! (other instanceof LongFilter otherFilter)){
            return false;
        }

        if(otherFilter.getDbMinLong() != this.getDbMinLong()){
            return false;
        }

        if(otherFilter.getDbMaxLong() != this.getDbMaxLong()){
            return false;
        }

        if(otherFilter.getUserMinLong() != this.getUserMinLong()){
            return false;
        }

        //suppressed because it improves readability
        //noinspection RedundantIfStatement
        if(otherFilter.getUserMaxLong() != this.getUserMaxLong()){
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "LongFilter{" + super.toString() + ", " +
                "DB_MIN=" + DB_MIN +
                ", DB_MAX=" + DB_MAX +
                ", userMin=" + userMin +
                ", userMax=" + userMax +
                '}';
    }

    @Override
    Collection<Product> filterList(Collection<Product> inputs) {
        // Filter inputs based on min and max value.
        // Only filter and remove the input if it is below min or above max
      
        List<Product> filteredResults = new ArrayList<>();

        // loop over all the products in the list and access the correct attribute:
        for (Product productHit : inputs) {
            // Since there currently is only one attribute of type long, we can simply check on that

            // The else is redundant but kept in for readability
            //noinspection StatementWithEmptyBody
            if(this.getProductAttribute().equals("ean")){
                if (checkValue(productHit.getEan())){
                    continue;
                }
            } else {
                // This means the product attribute was neither of the ones we claim to be valid
                // This should not happen, although if it does, the product will not be filtered out
                // with the current implementation.

                // To make the product get filtered out uncomment below line
//                continue;

            }

            // We have survived the check, without continuing the loop, thus we add the product to the filtered list
            filteredResults.add(productHit);
        }

        return filteredResults;
    }

    /**
     * This method is used to check whether the value of a product
     * lies within the range specified by this filter.
     *
     * @param value The value to check
     * @return true if the value is outside the range specified by the filter
     */
    private boolean checkValue(long value) {
        if (this.userMin != this.userMax) {
            return value < this.userMin || value > this.userMax;
        }

        return value < this.DB_MIN || value > this.DB_MAX;
    }

    /**
     * This method is used to check whether the value of a product
     * lies within the range specified by this filter.
     * This method is used when the product attribute is optional
     * (i.e. it may not be set on the product, and thus may have been only been initialized to 0)
     *
     * Note:<br>
     * Currently unused but is here for future compatibility
     *
     * @param value - The value to check
     * @return - true if the value is outside the range specified, but different from zero
     */
    private boolean checkValueOptional(long value) {
        return value != 0 && checkValue(value);
    }

    @Override
    public long getDbMinLong() {
        return DB_MIN;
    }

    @Override
    public long getDbMaxLong() {
        return DB_MAX;
    }

    @Override
    public long getUserMinLong() {
        return userMin;
    }

    @Override
    public long getUserMaxLong() {
        return userMax;
    }

    @Override
    public long setUserMin(long userMin) {
        this.userMin = userMin;
        return this.userMin;
    }

    @Override
    public long setUserMax(long userMax) {
        this.userMax = userMax;
        return this.userMax;
    }
}

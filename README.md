# CSVmodification

1. Extract data from file 'products.csv', apply some transofrmations and save the result to another file 'result.csv'.

2. Input file 'products.csv':
  a. File-encoding: UTF 8
  b. Separator-character: semicolon(;)
  c. Quote-character: double-quotes(")
  
3. Output file 'result.csv'
  a. File-encoding: ISO 8859-1
  b. Separator-character: pipe-symbol (|)
  c. Quote-character: single-quotes (')
  
4. Transformations
  Column in result.csv:
  name:                  Same content as 'Product Name' from input file;
  offerurl:              Content from column 'Link' of input file + the content of column 'SKU' as value query parameter 'id';
  price:                 Only the numeric value of column 'Selling-Price' with no grouping separator and dot (.) as decimal separator;
  published:             Column 'description' can contain a release-date of the product. The value in the output file should have the following format: DD.MM.YYYY;
  description:           Same content as column 'description' from input file.

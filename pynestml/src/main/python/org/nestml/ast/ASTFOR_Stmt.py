"""
@author kperun
TODO header
"""


class ASTFOR_Stmt:
    """
    This class is used to store a for-block.
    Grammar:
        for_Stmt : 'for' var=NAME 'in' vrom=expression 
                    '...' to=expression 'step' step=signedNumericLiteral BLOCK_OPEN block BLOCK_CLOSE;
    """
    __variable = None
    __from = None
    __to = None
    __step = None
    __block = None

    def __init__(self, _variable=None, _from=None, _to=None, _step=0, _block=None):
        """
        Standard constructor.
        :param _variable: the step variable used for iteration.
        :type _variable: str
        :param _from: left bound of the range, i.e., start value.
        :type _from: ASTExpression
        :param _to: right bound of the range, i.e., finish value.
        :type _to: ASTExpression
        :param _step: the length of a single step.
        :type _step: float
        :param _block: a block of statements.
        :type _block: ASTBlock
        """
        self.__block = _block
        self.__step = _step
        self.__to = _to
        self.__from = _from
        self.__variable = _variable

    @classmethod
    def makeASTFOR_Stmt(cls, _variable=None, _from=None, _to=None, _step=0, _block=None):
        """
        The factory method of the ASTFOR_Stmt class.
        :param _variable: the step variable used for iteration.
        :type _variable: str
        :param _from: left bound of the range, i.e., start value.
        :type _from: ASTExpression
        :param _to: right bound of the range, i.e., finish value.
        :type _to: ASTExpression
        :param _step: the length of a single step.
        :type _step: float
        :param _block: a block of statements.
        :type _block: ASTBlock 
        :return: a new ASTFOR_Stmt object.
        :rtype: ASTFOR_Stmt
        """
        return cls(_variable, _from, _to, _step, _block)

    def getVariable(self):
        """
        Returns the name of the step variable.
        :return: the name of the step variable.
        :rtype: str
        """
        return self.__variable

    def getFrom(self):
        """
        Returns the from-statement.
        :return: the expression indicating the start value.
        :rtype: ASTExpression
        """
        return self.__from

    def getTo(self):
        """
        Returns the to-statement.
        :return: the expression indicating the finish value.
        :rtype: ASTExpression
        """
        return self.__to

    def getStep(self):
        """
        Returns the length of a single step.
        :return: the length as a float.
        :rtype: float
        """
        return self.__step

    def getBlock(self):
        """
        Returns the block of statements.
        :return: the block of statements.
        :rtype: ASTBlock
        """
        return self.__block

    def printAST(self):
        """
        Returns a string representation of the for statement.
        :return: a string representing the for statement.
        :rtype: str
        """
        return 'for ' + self.getVariable().printAST() + ' in ' + self.getFrom().printAST() + '...' \
               + self.getTo().printAST() + ' step ' + self.getStep() + ':\n' + self.getBlock().printAST() + '\nend'
